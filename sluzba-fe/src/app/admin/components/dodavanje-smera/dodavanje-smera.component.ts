import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from '../../service/admin.service'; // Adjust the path to your service

@Component({
  selector: 'app-dodavanje-smera',
  templateUrl: './dodavanje-smera.component.html',
  styleUrls: ['./dodavanje-smera.component.css'],
})
export class DodavanjeSmeraComponent implements OnInit {
  smer = {
    naziv: '',
  };

  constructor(private adminService: AdminService, private router: Router) {}

  ngOnInit(): void {}

  submit(): void {
    this.adminService.dodajSmer(this.smer.naziv).subscribe(
      (response) => {
        console.log('Smer added successfully:', response);
        this.router.navigate(['/path-to-navigate-after-success']); // Adjust the path as necessary
      },
      (error) => {
        console.error('Error adding smer:', error);
      }
    );
  }

  goBack(): void {
    this.router.navigate(['/path-to-previous-page']); // Adjust the path as necessary
  }
}
