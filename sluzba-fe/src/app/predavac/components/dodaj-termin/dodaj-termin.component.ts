import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
import { PredavacService } from '../../services/predavac.service';
import { Termin } from 'src/app/core/models/termin.model';

@Component({
  selector: 'app-dodaj-termin',
  templateUrl: './dodaj-termin.component.html',
  styleUrls: ['./dodaj-termin.component.css'],
})
export class DodajTerminComponent implements OnInit {
  termin: Termin = new Termin();
  predmeti: any = [];
  tipoviPolaganja: any = [];

  constructor(
    private _location: Location,
    private predavacService: PredavacService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.predavacService.sviPredmeti().subscribe((res) => {
      this.predmeti = res;
    });
  }

  onChangePredmet(event): void {
    console.log('upadaaaa' + event.value);
    if (event.value) {
      this.predavacService
        .tipoviPolaganjaZaPredmet(event.value)
        .subscribe((res) => {
          this.tipoviPolaganja = res;
        });
    } else {
      this.tipoviPolaganja = [];
    }
  }

  save() {
    if (
      !this.termin.predmetId ||
      !this.termin.tipPolaganjaId ||
      !this.termin.napomena ||
      !this.termin.nazivRoka
    ) {
      this.toastr.error(
        'Tip polaganja, predmet, napomena i naziv roka su obavezna polja!'
      );
    } else {
      this.predavacService.dodajTermin(this.termin).subscribe(
        () => {
          this.toastr.success('Uspešno!');
        },
        (err) => {
          this.toastr.error(err.error.message);
        }
      );
    }
  }

  goBack() {
    this._location.back();
  }
}
