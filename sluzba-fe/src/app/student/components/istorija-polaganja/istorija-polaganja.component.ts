import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
import { MatTableDataSource } from '@angular/material/table';
import { StudentService } from '../../service/student.service';

@Component({
  selector: 'app-istorija-polaganja',
  templateUrl: './istorija-polaganja.component.html',
  styleUrls: ['./istorija-polaganja.component.css'],
})
export class IstorijaPolaganjaComponent implements OnInit {
  istorija: any = [];
  displayedColumns: string[] = [
    'vremePrijave',
    'nazivPredmeta',
    'nazivRoka',
    'ostvarenBrojBodova',
    'finalnaOcena',
  ];
  dataSource = new MatTableDataSource<any>(this.istorija);

  constructor(
    private _location: Location,
    private studentService: StudentService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.studentService.istorijatPolaganjaIspita().subscribe((res) => {
      this.istorija = res;
      this.dataSource = new MatTableDataSource<any>(res);
      this.dataSource.paginator = this.istorija;
    });
  }

  goBack() {
    this._location.back();
  }
}
