import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/core/models/user.model';
import { PredavacService } from '../../services/predavac.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
  user: User = new User();
  isEditable: boolean = false;
  loggedInUserEmail: string;

  constructor(
    private _location: Location,
    private predavacService: PredavacService,
    private toastr: ToastrService,
    private router: Router
  ) {}

  ngOnInit() {
    this.predavacService.getMyProfile().subscribe(
      (res) => {
        this.user = res;
      },
      () => this.router.navigate(['auth'])
    );

    this.loggedInUserEmail = localStorage.getItem('email');
  }

  edit() {
    this.isEditable = true;
  }

  save() {
    if (!this.user.ime || !this.user.prezime) {
      this.toastr.error('Ime i prezime su obavezna polja!');
    } else {
      this.predavacService.editProfile(this.user).subscribe(
        () => {
          this.toastr.success('Uspešno!');
          this.isEditable = false;
        },
        (err) => {
          this.toastr.error(err.error.message);
        }
      );
    }
  }

  isValidPhone(phone) {
    if (!phone) {
      return true;
    }
    const re = /([+]?\d{1,2}[.-\s]?)?(\d{3}[.-]?){2}\d{3}/;
    return re.test(String(phone));
  }

  goBack() {
    this._location.back();
  }
}
