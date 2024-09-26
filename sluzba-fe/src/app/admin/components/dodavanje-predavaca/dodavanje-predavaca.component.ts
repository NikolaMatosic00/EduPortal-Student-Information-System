import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dodavanje-predavaca',
  templateUrl: './dodavanje-predavaca.component.html',
  styleUrls: ['./dodavanje-predavaca.component.css'],
})
export class DodavanjePredavacaComponent {
  predavac = {
    ime: '',
    prezime: '',
    email: '',
    pass: '',
  };

  constructor(private adminService: AdminService, private router: Router) {}

  submit() {
    this.adminService.dodajPredavaca(this.predavac).subscribe(
      (response) => {
        console.log('Predavač je uspešno dodat', response);
        this.router.navigate(['/admin']);
      },
      (error) => {
        console.error('Došlo je do greške prilikom dodavanja predavača', error);
        // Show an error message
      }
    );
  }

  goBack() {
    this.router.navigate(['/admin']);
  }
}
