import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
import { MatTableDataSource } from '@angular/material/table';
import { StudentService } from '../../service/student.service';

@Component({
  selector: 'app-raspored',
  templateUrl: './raspored.component.html',
  styleUrls: ['./raspored.component.css'],
})
export class RasporedComponent implements OnInit {
  raspored: any = [];
  displayedColumns: string[] = ['nazivPredmeta', 'tekst'];
  dataSource = new MatTableDataSource<any>(this.raspored);

  constructor(
    private _location: Location,
    private studentService: StudentService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.studentService.pregledRasporeda().subscribe((res) => {
      this.raspored = res;
      this.dataSource = new MatTableDataSource<any>(res);
      this.dataSource.paginator = this.raspored;
    });
  }

  goBack() {
    this._location.back();
  }
}
