import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AutentificationService } from '../../services/autentification.service';

@Component({
  selector: 'app-change-email',
  templateUrl: './change-email.component.html',
  styleUrls: ['./change-email.component.css'],
})
export class ChangeEmailComponent implements OnInit {
  novEmail: String = '';
  novEmail2: String = '';

  constructor(
    private toastr: ToastrService,
    private router: Router,
    private auth: AutentificationService
  ) {}

  ngOnInit() {}

  validate() {
    if (!this.novEmail || !this.novEmail2) {
      this.toastr.warning('Sva polja su obavezna!');
      return false;
    } else if (this.novEmail !== this.novEmail2) {
      this.toastr.error('Emailovi se ne podudaraju!');
      return false;
    }
    return true;
  }

  confirmChangeEmail() {
    if (this.validate()) {
      this.auth.changeEmail(this.novEmail).subscribe(
        () => {
          this.toastr.success('Uspešno promenjen email, ulogujte se');
          this.auth.logout();
          this.router.navigate(['auth']);
        },
        (err) => {
          this.toastr.error(err);
        }
      );
    }
  }
}
