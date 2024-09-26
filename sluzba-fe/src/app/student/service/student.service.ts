import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { User } from 'src/app/core/models/user.model';

@Injectable({
  providedIn: 'root',
})
export class StudentService {
  private editProfileUrl = environment.baseUrl + '/student/azuriranjeProfila';
  private getProfileUrl = environment.baseUrl + '/student/getProfile';
  private getDobaviUplateUrl =
    environment.baseUrl + '/student/uplateZaStudenta';
  private dobaviPredmeteUrl = environment.baseUrl + '/student/pregledPredmeta';
  private getPregledRasporedaUrl =
    environment.baseUrl + '/student/pregledRasporeda';
  private getIstorijatPolaganjaIspitaUrl =
    environment.baseUrl + '/student/istorijatPolaganjaIspita';

  constructor(public http: HttpClient) {}

  editProfile(user: User): Observable<any> {
    return this.http.put(this.editProfileUrl, user);
  }

  getMyProfile(): Observable<any> {
    return this.http.get(this.getProfileUrl);
  }

  dobaviUplate(): Observable<any> {
    return this.http.get(this.getDobaviUplateUrl);
  }

  dobaviTrenutnoStanje() {
    return this.http.get<number>(
      environment.baseUrl + '/student/trenutnoStanje'
    );
  }

  overiSemestar(semestarId: number) {
    return this.http.post(
      `${environment.baseUrl}/student/overiSemestar?semestarId=${semestarId}`,
      {} // Prazno telo, ako ti ne treba dodatni sadržaj
    );
  }

  prijaviZaPolaganje(req: { idTerminPolaganja: number }): Observable<any> {
    return this.http.post(
      environment.baseUrl + '/student/prijavaZaPolaganje',
      req
    );
  }

  dobaviPredmete(): Observable<any> {
    return this.http.get(this.dobaviPredmeteUrl + '?filter=sve');
  }

  dobaviTermine(): Observable<any[]> {
    return this.http.get<any[]>(environment.baseUrl + '/student/dobaviTermine');
  }

  dobaviPredmeteZaPolaganje(): Observable<any> {
    return this.http.get(this.dobaviPredmeteUrl + '?filter=polaganje');
  }

  pregledRasporeda(): Observable<any> {
    return this.http.get(this.getPregledRasporedaUrl);
  }

  istorijatPolaganjaIspita(): Observable<any> {
    return this.http.get(this.getIstorijatPolaganjaIspitaUrl);
  }
}
