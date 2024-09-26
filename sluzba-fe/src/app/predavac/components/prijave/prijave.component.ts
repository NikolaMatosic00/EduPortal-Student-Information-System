import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
import { PredavacService } from '../../services/predavac.service';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-prijave',
  templateUrl: './prijave.component.html',
  styleUrls: ['./prijave.component.css'],
})
export class Prijaveomponent implements OnInit {
  idTermin: any;
  termini: any = [];
  polaganja: any = [];
  displayedColumns: string[] = [
    'imeStudenta',
    'prezimeStudenta',
    'ostvarenBrojBodova',
    'finalnaOcena',
    'istorija',
  ];
  dataSource = new MatTableDataSource<any>(this.polaganja);

  istorija: any = [];
  displayedColumnsIstorija: string[] = [
    'nazivPredmeta',
    'vremePrijave',
    'ostvarenBrojBodova',
    'finalnaOcena',
    'nazivRoka',
  ];
  dataSourceIstorija = new MatTableDataSource<any>(this.polaganja);

  constructor(
    private _location: Location,
    private predavacService: PredavacService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.predavacService.dobaviTermine().subscribe((res) => {
      this.termini = res;
    });
  }

  onChangeTermin(event): void {
    if (event.value) {
      this.predavacService.prijaveZaTermin(event.value).subscribe((res) => {
        this.polaganja = res;
        this.dataSource = new MatTableDataSource<any>(res);
        this.dataSource.paginator = this.polaganja;
      });
    } else {
      this.polaganja = [];
    }
  }

  istorijaPolaganja(element): void {
    if (element.idPredmet && element.idStudent) {
      this.predavacService
        .istorijaPrijavaStudenta(element.idStudent, element.idPredmet)
        .subscribe((res) => {
          this.istorija = res;
          this.dataSourceIstorija = new MatTableDataSource<any>(res);
          this.dataSourceIstorija.paginator = this.istorija;
        });
    } else {
      this.istorija = [];
    }
  }

  goBack() {
    this._location.back();
  }
}
