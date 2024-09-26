import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
import { MatTableDataSource } from '@angular/material/table';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-uplate',
  templateUrl: './uplate.component.html',
  styleUrls: ['./uplate.component.css'],
})
export class UplateComponent implements OnInit {
  uplate: any = [];
  displayedColumns: string[] = [
    'iznos',
    'svrha',
    'vremeUplate',
    'imeStudenta',
    'prezimeStudenta',
  ];
  dataSource = new MatTableDataSource<any>(this.uplate);

  constructor(
    private _location: Location,
    private adminService: AdminService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.adminService.dobaviUplate().subscribe((res) => {
      this.uplate = res;
      this.dataSource = new MatTableDataSource<any>(res);
      this.dataSource.paginator = this.uplate;
    });
  }

  goBack() {
    this._location.back();
  }
}
