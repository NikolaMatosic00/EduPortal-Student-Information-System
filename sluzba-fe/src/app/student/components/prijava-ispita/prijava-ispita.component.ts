import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { StudentService } from '../../service/student.service';

@Component({
  selector: 'app-prijava-ispita',
  templateUrl: './prijava-ispita.component.html',
  styleUrls: ['./prijava-ispita.component.css'],
})
export class PrijavaIspitaComponent implements OnInit {
  terminiPolaganja: any[] = [];
  selectedTerminId: number | null = null;

  constructor(
    private studentService: StudentService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.dobaviTerminePolaganja();
  }

  dobaviTerminePolaganja(): void {
    this.studentService.dobaviTermine().subscribe(
      (res) => {
        this.terminiPolaganja = res;
      },
      (error) => {
        this.toastr.error('Greška prilikom učitavanja termina polaganja!');
      }
    );
  }

  prijaviIspit(): void {
    if (this.selectedTerminId !== null) {
      const req = { idTerminPolaganja: this.selectedTerminId };

      this.studentService.prijaviZaPolaganje(req).subscribe(
        () => {
          this.toastr.success('Uspešno ste se prijavili za polaganje ispita!');
          this.selectedTerminId = null; // Resetuj izbor
        },
        (error) => {
          this.toastr.error('Greška prilikom prijave za ispit!');
        }
      );
    } else {
      this.toastr.warning('Molimo izaberite termin polaganja!');
    }
  }
}
