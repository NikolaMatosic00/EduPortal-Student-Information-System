import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
import { PredavacService } from '../../services/predavac.service';

@Component({
  selector: 'app-unesi-bodove',
  templateUrl: './unesi-bodove.component.html',
  styleUrls: ['./unesi-bodove.component.css'],
})
export class UnesiBodoveComponent implements OnInit {
  idPolaganje: any;
  idTermin: any;
  ostvarenBrojBodova: any;
  termini: any = [];
  polaganja: any = [];

  constructor(
    private _location: Location,
    private predavacService: PredavacService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.predavacService.dobaviTermine().subscribe((res) => {
      this.termini = res;
      console.log(res);
    });
  }

  onChangeTermin(event): void {
    if (event.value) {
      this.predavacService
        .dobaviPolaganjaZaTermin(event.value)
        .subscribe((res) => {
          this.polaganja = res;
        });
    } else {
      this.polaganja = [];
    }
  }

  save() {
    if (!this.idPolaganje || !this.ostvarenBrojBodova) {
      this.toastr.error('Polaganje i bodovi su obavezna polja!');
    } else {
      this.predavacService
        .unesiBrojBodova(this.idPolaganje, this.ostvarenBrojBodova)
        .subscribe(
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
