import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
import { MatTableDataSource } from '@angular/material/table';
import { StudentService } from '../../service/student.service';

@Component({
  selector: 'app-predmeti-polaganje',
  templateUrl: './predmeti-polaganje.component.html',
  styleUrls: ['./predmeti-polaganje.component.css'],
})
export class PredmetiPolaganjeComponent implements OnInit {
  predmeti: any = [];
  displayedColumns: string[] = [
    'naziv',
    'silabus',
    'tip',
    'finalnaOcena',
    'potpisan',
    'espb',
    'detalji',
  ];
  dataSource = new MatTableDataSource<any>(this.predmeti);

  constructor(
    private _location: Location,
    private studentService: StudentService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.studentService.dobaviPredmeteZaPolaganje().subscribe((res) => {
      this.predmeti = res;
      this.dataSource = new MatTableDataSource<any>(res);
      this.dataSource.paginator = this.predmeti;
    });
  }

  goBack() {
    this._location.back();
  }

  detaljiPredmeta(element) {
    //TODO /student/detaljiPredmeta?id=XYZ
  }
}
