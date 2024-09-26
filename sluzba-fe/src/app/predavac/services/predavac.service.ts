import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { User } from 'src/app/core/models/user.model';
import { Obavestenje } from 'src/app/core/models/obavestenje.model';
import { Termin } from 'src/app/core/models/termin.model';

@Injectable({
  providedIn: 'root',
})
export class PredavacService {
  private editProfileUrl = environment.baseUrl + '/predavac/azuriranjeProfila';
  private getProfileUrl = environment.baseUrl + '/predavac/getProfile';
  private obavestenjeUrl = environment.baseUrl + '/predavac/postaviObavestenje';
  private getSviPredmetiUrl = environment.baseUrl + '/public/sviPredmeti';
  private getTipoviPolaganjaZaPredmetUrl =
    environment.baseUrl + '/predavac/tipoviPolaganjaZaPredmet';
  private dodajTerminUrl = environment.baseUrl + '/predavac/dodajTermin';
  private elektronskiPotpisUrl =
    environment.baseUrl + '/predavac/elektronskiPotpis';
  private unesiOcenuUrl = environment.baseUrl + '/predavac/unesiOcenu';
  private getDobaviSlusanjaUrl =
    environment.baseUrl + '/predavac/dobaviSlusanja';
  private getDobaviTermineUrl = environment.baseUrl + '/predavac/dobaviTermine';
  private getDobaviPolaganjaZaTerminUrl =
    environment.baseUrl + '/predavac/dobaviPolaganjaZaTermin';
  private unesiBrojBodovaUrl =
    environment.baseUrl + '/predavac/unesiBrojBodova';
  private getPrijaveZaTerminUrl =
    environment.baseUrl + '/predavac/prijaveZaTermin';
  private getIstorijaPrijavaStudentaUrl =
    environment.baseUrl + '/predavac/istorijaPrijavaStudenta';

  constructor(public http: HttpClient) {}

  editProfile(user: User): Observable<any> {
    return this.http.put(this.editProfileUrl, user);
  }

  getMyProfile(): Observable<any> {
    return this.http.get(this.getProfileUrl);
  }

  postaviObavestenje(obavestenje: Obavestenje): Observable<any> {
    return this.http.post(this.obavestenjeUrl, obavestenje);
  }

  dodajTermin(termin: Termin): Observable<any> {
    return this.http.post(this.dodajTerminUrl, termin);
  }

  sviPredmeti(): Observable<any> {
    return this.http.get(this.getSviPredmetiUrl);
  }

  tipoviPolaganjaZaPredmet(predmetId: any): Observable<any> {
    return this.http.get(
      this.getTipoviPolaganjaZaPredmetUrl + '?idPredmeta=' + predmetId
    );
  }

  elektronskiPotpis(idSlusaPredmet: any): Observable<any> {
    return this.http.put(this.elektronskiPotpisUrl, { idSlusaPredmet });
  }

  unesiOcenu(idSlusaPredmet: any, ocena: any): Observable<any> {
    return this.http.put(this.unesiOcenuUrl, { idSlusaPredmet, ocena });
  }

  unesiBrojBodova(idPolaganje: any, ostvarenBrojBodova: any): Observable<any> {
    return this.http.put(this.unesiBrojBodovaUrl, {
      idPolaganje,
      ostvarenBrojBodova,
    });
  }

  dobaviSlusanja(potpis: any): Observable<any> {
    return this.http.get(this.getDobaviSlusanjaUrl + '?potpis=' + potpis);
  }

  dobaviTermine(): Observable<any> {
    return this.http.get(this.getDobaviTermineUrl);
  }

  dobaviPolaganjaZaTermin(idTermina: any): Observable<any> {
    return this.http.get(
      this.getDobaviPolaganjaZaTerminUrl + '?idTermina=' + idTermina
    );
  }

  prijaveZaTermin(id: any): Observable<any> {
    return this.http.get(this.getPrijaveZaTerminUrl + '?id=' + id);
  }

  istorijaPrijavaStudenta(idStudenta: any, idPredmeta: any): Observable<any> {
    return this.http.get(
      this.getIstorijaPrijavaStudentaUrl +
        '?idStudenta=' +
        idStudenta +
        '&idPredmeta=' +
        idPredmeta
    );
  }
}
