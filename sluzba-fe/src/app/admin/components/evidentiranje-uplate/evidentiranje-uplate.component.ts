import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-evidentiranje-uplate',
  templateUrl: './evidentiranje-uplate.component.html',
  styleUrls: ['./evidentiranje-uplate.component.css'],
})
export class EvidentiranjeUplateComponent implements OnInit {
  uplataForm!: FormGroup;
  studenti: any[] = [];

  constructor(private fb: FormBuilder, private adminService: AdminService) {}

  ngOnInit(): void {
    this.uplataForm = this.fb.group({
      iznos: ['', [Validators.required, Validators.min(0.01)]],
      svrha: ['', Validators.required],
      idStudent: ['', Validators.required],
    });

    // Fetch students and populate dropdown
    this.adminService.getStudenti().subscribe(
      (data) => {
        this.studenti = data;
      },
      (error) => {
        console.error('Error fetching students:', error);
      }
    );
  }

  submit(): void {
    if (this.uplataForm.valid) {
      this.adminService.evidentirajUplatu(this.uplataForm.value).subscribe(
        (response) => {
          console.log('Uplata evidentirana successfully:', response);
        },
        (error) => {
          console.error('Error evidentiranja uplate:', error);
        }
      );
    }
  }
}
