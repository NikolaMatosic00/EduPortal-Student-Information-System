import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dodaj-ulogu-na-predmet',
  templateUrl: './dodaj-ulogu-na-predmet.component.html',
  styleUrls: ['./dodaj-ulogu-na-predmet.component.css'],
})
export class DodajUloguNaPredmetComponent implements OnInit {
  predmeti: any[] = [];
  nastavnici: any[] = [];
  selectedPredmet: number | null = null;
  selectedNastavnik: number | null = null;
  selectedUloga: string = '';

  roles: string[] = ['PROFESOR', 'ASISTENT'];

  constructor(private adminService: AdminService, private router: Router) {}

  ngOnInit(): void {
    this.loadPredmeti();
    this.loadNastavnici();
  }

  loadPredmeti(): void {
    this.adminService.getPredmeti().subscribe((data) => {
      this.predmeti = data;
    });
  }

  loadNastavnici(): void {
    this.adminService.getNastavnici().subscribe((data) => {
      this.nastavnici = data;
    });
  }

  assignTeacher(): void {
    const assignment = {
      idPredmet: this.selectedPredmet,
      idPredavac: this.selectedNastavnik,
      uloga: this.selectedUloga,
    };

    this.adminService.assignTeacher(assignment).subscribe((response) => {
      console.log('Nastavnik uspešno dodeljen predmetu:', response);
      this.router.navigate(['/admin']); // Redirect after assignment
    });
  }

  goBack(): void {
    this.router.navigate(['/admin']);
  }
}
