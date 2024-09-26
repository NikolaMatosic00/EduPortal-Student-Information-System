import { Injectable } from '@angular/core';
import { Subject, Observable, of } from 'rxjs';
import { getDateTimeInBelgradeFromString } from 'src/app/core/helper/dateFormatter';

@Injectable({
  providedIn: 'root',
})
export class LocalStorageService {
  private storageSub = new Subject<String>();
  private itemsSub = new Subject<Boolean>();
  private bonusPointsSub = new Subject<Boolean>();

  constructor() {}

  watchStorage(): Observable<any> {
    return this.storageSub.asObservable();
  }

  roleChanged(role: string | String | undefined) {
    this.storageSub.next(role);
  }

  logout() {
    this.storageSub.next('');
  }
}
