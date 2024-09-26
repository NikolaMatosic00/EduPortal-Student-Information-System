import { AutofillMonitor } from '@angular/cdk/text-field';
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { AutentificationService } from 'src/app/autentification/services/autentification.service';
import { LocalStorageService } from '../../service/local-storage-service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css'],
})
export class NavigationComponent implements OnInit {
  mode = new FormControl('push');
  roleAdmin: Boolean = false;
  rolePredavac: Boolean = false;
  roleStudent: Boolean = false;

  constructor(
    private storageService: LocalStorageService,
    private authService: AutentificationService
  ) {
    this.storageService.watchStorage().subscribe((role: string) => {
      let authorities = [JSON.parse(role)];
      console.log(authorities);
      for (const auth of authorities) {
        if (auth === 'ADMIN') {
          this.roleAdmin = true;
        } else if (auth === 'PREDAVAC') {
          console.log('upada');
          this.rolePredavac = true;
        } else if (auth === 'STUDENT') {
          this.roleStudent = true;
        }
      }
    });
  }

  ngOnInit() {
    let authorities = [JSON.parse(`${this.authService.getUserRole()}`)];
    console.log(authorities);
    for (const auth of authorities) {
      if (auth === 'ADMIN') {
        this.roleAdmin = true;
      } else if (auth === 'PREDAVAC') {
        console.log('upada');
        this.rolePredavac = true;
      } else if (auth === 'STUDENT') {
        this.roleStudent = true;
      }
    }
  }

  logout() {
    this.authService.logout();
    this.storageService.logout();
    this.roleAdmin = false;
    this.rolePredavac = false;
    this.roleStudent = false;
  }
}
