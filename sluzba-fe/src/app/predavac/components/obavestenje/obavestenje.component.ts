import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
import { PredavacService } from '../../services/predavac.service';
import { Obavestenje } from 'src/app/core/models/obavestenje.model';

@Component({
  selector: 'app-obavestenje',
  templateUrl: './obavestenje.component.html',
  styleUrls: ['./obavestenje.component.css'],
})
export class ObavestenjeComponent implements OnInit {
  obavestenje: Obavestenje = new Obavestenje();
  predmeti: any = [];

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

  save() {
    console.log(this.obavestenje);
    if (!this.obavestenje.tekst || !this.obavestenje.idPredmet) {
      this.toastr.error('Tekst i predmet su obavezna polja!');
    } else {
      this.predavacService.postaviObavestenje(this.obavestenje).subscribe(
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
