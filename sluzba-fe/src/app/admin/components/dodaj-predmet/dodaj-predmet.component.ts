import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-dodaj-predmet',
  templateUrl: './dodaj-predmet.component.html',
  styleUrls: ['./dodaj-predmet.component.css'],
})
export class DodajPredmetComponent implements OnInit {
  predmet: any = {
    naziv: '',
    espb: 0,
    idSmer: null,
    idSemestar: null,
    tipPolaganja: [],
    silabus: '',
    tip: '',
  };
  smerovi: any[] = [];
  semestri: any[] = [];
  tipoviPolaganja: any[] = [];

  constructor(
    private _location: Location,
    private adminService: AdminService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.adminService.getSmerovi().subscribe((res) => {
      this.smerovi = res;
    });

    this.adminService.getSemestari().subscribe((res) => {
      this.semestri = res;
    });

    this.adminService.getTipoviPolaganja().subscribe((res) => {
      this.tipoviPolaganja = res;
    });
  }

  save(): void {
    if (
      !this.predmet.naziv ||
      !this.predmet.espb ||
      !this.predmet.idSmer ||
      !this.predmet.idSemestar ||
      !this.predmet.tipoviPolaganja.length ||
      !this.predmet.silabus ||
      !this.predmet.tip
    ) {
      this.toastr.error('Sva polja su obavezna!');
    } else {
      this.adminService.dodajPredmet(this.predmet).subscribe(
        () => {
          this.toastr.success('Predmet uspešno dodat!');
        },
        (err) => {
          this.toastr.error(err.error.message);
        }
      );
    }
  }

  goBack(): void {
    this._location.back();
  }
}
