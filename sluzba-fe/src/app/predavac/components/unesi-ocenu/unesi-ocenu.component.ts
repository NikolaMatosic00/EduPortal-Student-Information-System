import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
import { PredavacService } from '../../services/predavac.service';

@Component({
  selector: 'app-unesi-ocenu',
  templateUrl: './unesi-ocenu.component.html',
  styleUrls: ['./unesi-ocenu.component.css'],
})
export class UnesiOcenuComponent implements OnInit {
  idSlusaPredmet: any;
  ocena: any;
  slusanja: any = [];

  constructor(
    private _location: Location,
    private predavacService: PredavacService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    const statusPotpisa = 1;
    this.predavacService.dobaviSlusanja(statusPotpisa).subscribe((res) => {
      this.slusanja = res;
    });
  }

  save() {
    if (!this.idSlusaPredmet || !this.ocena) {
      this.toastr.error('Slusanje i ocena su obavezna polja!');
    } else if (this.ocena < 5 || this.ocena > 10) {
      this.toastr.error('Ocena mora biti u rasponu 5 do 10!');
    } else {
      this.predavacService
        .unesiOcenu(this.idSlusaPredmet, this.ocena)
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
