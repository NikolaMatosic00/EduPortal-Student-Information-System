import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminService } from '../../service/admin.service'; // Adjust the path as necessary

@Component({
  selector: 'app-dodavanje-studenta',
  templateUrl: './dodavanje-studenta.component.html',
  styleUrls: ['./dodavanje-studenta.component.css'],
})
export class DodavanjeStudentaComponent implements OnInit {
  student = {
    ime: '',
    prezime: '',
    email: '',
    pass: '',
    brojIndeksa: '',
    smerId: '',
  };
  smerovi: any[] = []; // Replace with appropriate type for your Smer object

  constructor(private adminService: AdminService, private router: Router) {}

  ngOnInit(): void {
    this.getSmerovi(); // Fetch departments on initialization
  }

  getSmerovi(): void {
    this.adminService.getSmerovi().subscribe(
      (data) => {
        this.smerovi = data; // Assuming the API returns an array of Smer
      },
      (error) => {
        console.error('Error fetching smerovi:', error);
      }
    );
  }

  submit(): void {
    this.adminService.dodajStudenta(this.student).subscribe(
      (response) => {
        console.log('Student added successfully:', response);
        this.router.navigate(['/path-to-navigate-after-success']); // Adjust the path as necessary
      },
      (error) => {
        console.error('Error adding student:', error);
      }
    );
  }

  goBack(): void {
    this.router.navigate(['/path-to-previous-page']); // Adjust the path as necessary
  }
}
