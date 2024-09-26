import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  private getDobaviUplateUrl = environment.baseUrl + '/admin/uplate';
  private getDodajPredmetUrl = environment.baseUrl + '/admin/dodajPredmet';
  private getSmeroviUrl = environment.baseUrl + '/admin/smerovi';
  private getSemestariUrl = environment.baseUrl + '/admin/semestari';
  private getTipoviPolaganjaUrl =
    environment.baseUrl + '/admin/tipoviPolaganja';

  // New URLs for fetching predmeti and nastavnici
  private getPredmetiUrl = environment.baseUrl + '/admin/predmeti';
  private getNastavniciUrl = environment.baseUrl + '/admin/predavaci';
  private getStudentiUrl = environment.baseUrl + '/admin/studenti';
  private getAssignTeacherUrl =
    environment.baseUrl + '/admin/dodajUloguNaPredmet';
  private getDodajStudentaUrl = environment.baseUrl + '/admin/dodajStudenta'; // New URL for adding a student
  private getDodajStudentaNaPredmetUrl =
    environment.baseUrl + '/admin/dodajNaPredmet'; // New URL for adding a student
  private evidentirajUplatuUrl =
    environment.baseUrl + '/admin/evidentirajUplatu';

  constructor(public http: HttpClient) {}

  dobaviUplate(): Observable<any> {
    return this.http.get(this.getDobaviUplateUrl);
  }

  dodajPredavaca(predavac: any): Observable<any> {
    return this.http.post(
      `${environment.baseUrl}/admin/dodajPredavaca`,
      predavac
    );
  }

  dodajStudenta(student: any): Observable<any> {
    // New method for adding a student
    return this.http.post(this.getDodajStudentaUrl, student);
  }

  dodajStudentaNaPredmet(
    studentId: number,
    predmetId: number
  ): Observable<any> {
    const payload = {
      studentId: studentId,
      predmetId: predmetId,
    };
    return this.http.post(this.getDodajStudentaNaPredmetUrl, payload);
  }

  dodajSmer(naziv: string): Observable<any> {
    const params = new HttpParams().set('naziv', naziv); // Setting the param in URL
    return this.http.post(environment.baseUrl + '/admin/dodajSmer', null, {
      params,
    });
  }

  dodajPredmet(predmet: any): Observable<any> {
    return this.http.post(this.getDodajPredmetUrl, predmet);
  }

  // Metoda za evidentiranje uplate
  evidentirajUplatu(uplata: {
    iznos: number;
    svrha: string;
    idStudent: number;
  }): Observable<any> {
    return this.http.post(this.evidentirajUplatuUrl, uplata);
  }

  // Fetch all available Smerovi (Departments)
  getSmerovi(): Observable<any> {
    return this.http.get(this.getSmeroviUrl);
  }

  // Fetch all available Semestari (Semesters)
  getSemestari(): Observable<any> {
    return this.http.get(this.getSemestariUrl);
  }

  // Fetch all available Tipovi Polaganja (Types of Exams)
  getTipoviPolaganja(): Observable<any> {
    return this.http.get(this.getTipoviPolaganjaUrl);
  }

  // Fetch all available Predmeti (Subjects)
  getPredmeti(): Observable<any[]> {
    return this.http.get<any[]>(this.getPredmetiUrl);
  }

  // Fetch all available Nastavnici (Teachers)
  getNastavnici(): Observable<any[]> {
    return this.http.get<any[]>(this.getNastavniciUrl);
  }

  // Fetch all available Nastavnici (Teachers)
  getStudenti(): Observable<any[]> {
    return this.http.get<any[]>(this.getStudentiUrl);
  }

  // Assign a teacher to a subject
  assignTeacher(assignment: any): Observable<any> {
    return this.http.post(this.getAssignTeacherUrl, assignment);
  }
}
