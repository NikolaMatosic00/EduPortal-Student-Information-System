import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
import { PredavacService } from '../../services/predavac.service';

@Component({
  selector: 'app-elektronski-potpis',
  templateUrl: './elektronski-potpis.component.html',
  styleUrls: ['./elektronski-potpis.component.css'],
})
export class ElektronskiPotpisComponent implements OnInit {
  idSlusaPredmet: any;
  slusanja: any = [];

  constructor(
    private _location: Location,
    private predavacService: PredavacService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    const statusPotpisa = 0;
    this.predavacService.dobaviSlusanja(statusPotpisa).subscribe((res) => {
      this.slusanja = res;
    });
  }

  save() {
    if (!this.idSlusaPredmet) {
      this.toastr.error('Slusanje je obavezno polje!');
    } else {
      this.predavacService.elektronskiPotpis(this.idSlusaPredmet).subscribe(
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
