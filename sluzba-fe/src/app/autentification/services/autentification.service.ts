import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpEvent } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AutentificationService {
  private loginUrl = environment.baseUrl + '/login';
  private changePasswordStudentUrl =
    environment.baseUrl + '/student/novaLozinka';
  private changeEmailStudentUrl = environment.baseUrl + '/student/novEmail';
  private changePasswordPredavacUrl =
    environment.baseUrl + '/predavac/novaLozinka';
  private changeEmailPredavacUrl = environment.baseUrl + '/predavac/novEmail';

  constructor(public http: HttpClient) {}

  loginUser(username: string, password: string): Observable<any> {
    return this.http.post(this.loginUrl, {
      email: username,
      pass: password,
    });
  }

  getUserRole(): String | null {
    return localStorage.getItem('role');
  }

  getUserUsername(): String | null {
    return localStorage.getItem('username');
  }

  logout() {
    localStorage.clear();
  }

  changePassword(novaLozinka: String): Observable<any> {
    console.log(this.getUserRole());
    if (this.getUserRole() === '"STUDENT"') {
      return this.http.post(this.changePasswordStudentUrl, { novaLozinka });
    } else if (this.getUserRole() === '"PREDAVAC"') {
      return this.http.post(this.changePasswordPredavacUrl, { novaLozinka });
    }
    return new Observable<HttpEvent<any>>();
  }

  changeEmail(email: String): Observable<any> {
    if (this.getUserRole() === '"STUDENT"') {
      return this.http.post(this.changeEmailStudentUrl, { email });
    } else if (this.getUserRole() === '"PREDAVAC"') {
      return this.http.post(this.changeEmailPredavacUrl, { email });
    }
    return new Observable<HttpEvent<any>>();
  }
}
