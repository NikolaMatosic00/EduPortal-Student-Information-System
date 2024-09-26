import { Component, OnInit, ViewChild } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { StudentService } from '../../service/student.service';
import { AdminService } from 'src/app/admin/service/admin.service';

@Component({
  selector: 'app-uplate',
  templateUrl: './uplate.component.html',
  styleUrls: ['./uplate.component.css'],
})
export class UplateComponent implements OnInit {
  uplate: any = [];
  trenutnoStanje: number = 0;
  semesters: any[] = []; // Dodaj niz za semestre
  selectedSemestarId: number | null = null; // Dodaj promenljivu za odabrani semestar
  displayedColumns: string[] = [
    'iznos',
    'svrha',
    'vremeUplate',
    'imeStudenta',
    'prezimeStudenta',
  ];
  dataSource = new MatTableDataSource<any>(this.uplate);

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private studentService: StudentService,
    private adminService: AdminService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.studentService.dobaviUplate().subscribe((res) => {
      this.uplate = res;
      this.dataSource = new MatTableDataSource<any>(res);
      this.dataSource.paginator = this.paginator;
    });

    this.studentService.dobaviTrenutnoStanje().subscribe((res) => {
      this.trenutnoStanje = res;
    });

    // Učitavanje semestara
    this.adminService.getSemestari().subscribe((res) => {
      this.semesters = res; // Čuvaj učitane semestre
    });
  }

  overiSemestar(): void {
    if (this.selectedSemestarId !== null) {
      this.studentService.overiSemestar(this.selectedSemestarId).subscribe(
        () => {
          this.toastr.success('Semestar uspešno overen!');
        },
        (error) => {
          this.toastr.error('Greška prilikom overe semestra!');
        }
      );
    } else {
      this.toastr.warning('Molimo izaberite semestar!');
    }
  }
}
