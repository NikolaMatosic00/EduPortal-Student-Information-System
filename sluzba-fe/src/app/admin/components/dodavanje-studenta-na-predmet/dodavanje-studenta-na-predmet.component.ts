import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from '../../service/admin.service'; // Adjust path as necessary

@Component({
  selector: 'app-dodavanje-studenta-na-predmet',
  templateUrl: './dodavanje-studenta-na-predmet.component.html',
  styleUrls: ['./dodavanje-studenta-na-predmet.component.css'],
})
export class DodavanjeStudentaNaPredmetComponent implements OnInit {
  assignment = {
    studentId: '',
    predmetId: '',
  };
  studenti: any[] = []; // List of students
  predmeti: any[] = []; // List of subjects

  constructor(private adminService: AdminService, private router: Router) {}

  ngOnInit(): void {
    this.getStudenti(); // Fetch all students on initialization
    this.getPredmeti(); // Fetch all subjects on initialization
  }

  // Fetch all students
  getStudenti(): void {
    this.adminService.getStudenti().subscribe(
      (data) => {
        this.studenti = data; // Populate studenti array
      },
      (error) => {
        console.error('Error fetching students:', error);
      }
    );
  }

  // Fetch all subjects (predmeti)
  getPredmeti(): void {
    this.adminService.getPredmeti().subscribe(
      (data) => {
        this.predmeti = data; // Populate predmeti array
      },
      (error) => {
        console.error('Error fetching subjects:', error);
      }
    );
  }

  // Submit the assignment of student to a subject
  submit(): void {
    this.adminService
      .dodajStudentaNaPredmet(
        Number.parseInt(this.assignment.studentId),
        Number.parseInt(this.assignment.predmetId)
      )
      .subscribe(
        (response) => {
          console.log('Student successfully assigned to subject:', response);
          this.router.navigate(['/path-to-navigate-after-success']); // Adjust path after success
        },
        (error) => {
          console.error('Error assigning student to subject:', error);
        }
      );
  }

  goBack(): void {
    this.router.navigate(['/path-to-previous-page']); // Adjust path as necessary
  }
}
