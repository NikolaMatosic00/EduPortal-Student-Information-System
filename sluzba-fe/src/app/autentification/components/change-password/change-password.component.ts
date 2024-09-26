import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AutentificationService } from '../../services/autentification.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css'],
})
export class ChangePasswordComponent implements OnInit {
  novaLozinka: String = '';
  novaLozinka2: String = '';

  constructor(
    private toastr: ToastrService,
    private router: Router,
    private auth: AutentificationService
  ) {}

  ngOnInit() {}

  validate() {
    if (!this.novaLozinka || !this.novaLozinka2) {
      this.toastr.warning('Sva polja su obavezna!');
      return false;
    } else if (this.novaLozinka !== this.novaLozinka2) {
      this.toastr.error('Lozinke se ne podudaraju!');
      return false;
    } else if (this.novaLozinka.length < 5) {
      this.toastr.error('Lozinka mora da sadrži minumum 5 karaktera!');
      return false;
    }
    return true;
  }

  confirmChangePassword() {
    if (this.validate()) {
      this.auth.changePassword(this.novaLozinka).subscribe(
        () => {
          this.toastr.success('Uspešno promenjena lozinka');
          this.router.navigate(['']);
        },
        (err) => {
          this.toastr.error(err);
        }
      );
    }
  }
}
