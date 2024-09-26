package com.studentska.sluzba.service.impl;

import com.studentska.sluzba.dto.predavac.DodavanjePredavacaDTO;
import com.studentska.sluzba.dto.predavac.PredavacDTO;
import com.studentska.sluzba.dto.predavac.ProfileDtoRes;
import com.studentska.sluzba.dto.predavac.TerminPolaganjaDtoRes;
import com.studentska.sluzba.dto.student.AzuriranjeProfilaDTOReq;
import com.studentska.sluzba.dto.student.DodajStudentaNaPredmetDTO;
import com.studentska.sluzba.dto.student.DodavanjeStudentaDTO;
import com.studentska.sluzba.dto.student.PredmetDTORes;
import com.studentska.sluzba.dto.student.StudentDTO;
import com.studentska.sluzba.dto.NovEmailDTOReq;
import com.studentska.sluzba.dto.NovaLozinkaDTOReq;
import com.studentska.sluzba.model.Predavac;
import com.studentska.sluzba.model.PredavacPredmet;
import com.studentska.sluzba.model.Predmet;
import com.studentska.sluzba.model.SlusaPredmet;
import com.studentska.sluzba.model.Smer;
import com.studentska.sluzba.model.Student;
import com.studentska.sluzba.model.StudentPohadja;
import com.studentska.sluzba.model.TerminPolaganja;
import com.studentska.sluzba.model.Uplata;
import com.studentska.sluzba.repository.PredmetRepository;
import com.studentska.sluzba.repository.SlusaPredmetRepository;
import com.studentska.sluzba.repository.SmerRepository;
import com.studentska.sluzba.repository.StudentRepository;
import com.studentska.sluzba.repository.TerminPolaganjaRepository;
import com.studentska.sluzba.security.SecurityConfiguration;
import com.studentska.sluzba.security.TokenUtils;
import com.studentska.sluzba.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	StudentRepository studentRepository;

	@Autowired
	SmerRepository smerRepository;
	
	@Autowired
	TerminPolaganjaRepository terminPolaganjaRepository;

	@Autowired
	SlusaPredmetRepository slusaPredmetRepository;

	@Autowired
	PredmetRepository predmetRepository;

	@Autowired
	SecurityConfiguration configuration;

	@Autowired
	TokenUtils tokenUtils;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JavaMailSender mailSender;

	@Override
	public void novEmail(String token, NovEmailDTOReq req) {
		String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
		Student student = studentRepository.findOneByEmail(username);
		student.setEmail(req.getEmail());
		studentRepository.save(student);
	}

	@Override
	public void novaLozinka(String token, NovaLozinkaDTOReq req) {
		String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
		Student student = studentRepository.findOneByEmail(username);
		student.setPass(configuration.passwordEncoder().encode(req.getNovaLozinka()));
		studentRepository.save(student);
	}

	@Override
	public void azuriranjeProfila(String token, AzuriranjeProfilaDTOReq req) {
		String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
		Student student = studentRepository.findOneByEmail(username);
		student.setIme(req.getIme());
		student.setPrezime(req.getPrezime());
		student.setBrojIndexa(req.getBrojIndeksa());
		studentRepository.save(student);
	}

	@Override
	public void uverenjeOStudiranju(String token) {
		String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
		Student student = studentRepository.findOneByEmail(username);
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			mimeMessageHelper.setSubject("Zaboravljena lozinka");
			mimeMessageHelper.setFrom("dummyemail@mail.com");
			mimeMessageHelper.setTo("administracija@mail.com");
			mimeMessageHelper.setText("Zahtev za uverenje o studiranju od " + student.getEmail() + " broj indeksa "
					+ student.getBrojIndexa() + " ime i prezime " + student.getIme() + " " + student.getPrezime());
			new Thread(() -> {
				mailSender.send(mimeMessageHelper.getMimeMessage());
			}).start();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ProfileDtoRes getProfile(String token) {
		String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
		Student student = studentRepository.findOneByEmail(username);
		ProfileDtoRes res = new ProfileDtoRes();
		res.setPrezime(student.getPrezime());
		res.setIme(student.getIme());
		res.setBrojIndeksa(student.getBrojIndexa());
		return res;
	}

	public void dodajStudenta(DodavanjeStudentaDTO dtoReq) {
		Student p = new Student();
		Optional<Smer> s = smerRepository.findById(Integer.parseInt(dtoReq.getSmerId()));
		p.setIme(dtoReq.getIme());
		p.setPrezime(dtoReq.getPrezime());
		p.setEmail(dtoReq.getEmail());
		p.setPass(passwordEncoder.encode(dtoReq.getPass()));
		p.setBrojIndexa(dtoReq.getBrojIndeksa());
		p.setSmer(s.get());
		Student pp = studentRepository.save(p);

	}

	public void dodajStudentaNaPredmet(DodajStudentaNaPredmetDTO dto) {
		// Find the student and course
		Student student = studentRepository.findById(dto.getStudentId())
				.orElseThrow(() -> new IllegalArgumentException("Student not found"));
		Predmet predmet = predmetRepository.findById(dto.getPredmetId())
				.orElseThrow(() -> new IllegalArgumentException("Predmet not found"));

		// Create a new SlusaPredmet entry to link the student and the course
		SlusaPredmet slusaPredmet = new SlusaPredmet();
		slusaPredmet.setStudent(student);
		slusaPredmet.setPredmet(predmet);

		// Save the SlusaPredmet entity
		slusaPredmetRepository.save(slusaPredmet);
	}

	@Override
	public List<StudentDTO> sviStudenti() {
		List<StudentDTO> res = new ArrayList<>();
		List<Student> sviStudenti = studentRepository.findAll();
		for (Student p : sviStudenti) {

			res.add(new StudentDTO(p.getId(), p.getBrojIndexa(), p.getIme(), p.getPrezime()));
		}
		return res;
	}

	@Override
	public int trenutnoStanje(String token) {
		String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
		Student student = studentRepository.findOneByEmail(username);
		int ukupno = 0;
		for (Uplata u : student.getUplatas()) {
			ukupno += u.getIznos();
		}
		for (StudentPohadja sp : student.getStudentPohadjas()) {
			ukupno -= 2500;
		}
		return ukupno;
	}

	@Override
	public List<TerminPolaganjaDtoRes> dobaviTermine(String token) {
		String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
		Student student = new Student();
		student = studentRepository.findOneByEmail(username);
		List<TerminPolaganjaDtoRes> res = new ArrayList<>();
		List<Integer> numbers = new ArrayList<>();
		
		for(SlusaPredmet predmet : student.getSlusaPredmets()) {
			numbers.add(predmet.getPredmet().getId());
		}
		
		for(TerminPolaganja t : terminPolaganjaRepository.findAll()) {
			if(numbers.contains(t.getPravilaPolaganja().getId().getPredmetId())) {
				Optional<Predmet> pred = predmetRepository.findById(t.getPravilaPolaganja().getId().getPredmetId());
				TerminPolaganjaDtoRes tmp = new TerminPolaganjaDtoRes();
				tmp.setId(t.getId());
				tmp.setPredmet(pred.get().getNaziv());
				tmp.setNazivRoka(t.getNazivRoka());
				tmp.setNapomena(t.getNapomena());
				res.add(tmp);
			}
		}
		return res;
	}
	
}
