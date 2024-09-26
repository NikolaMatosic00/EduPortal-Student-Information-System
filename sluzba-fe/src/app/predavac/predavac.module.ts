import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PredavacRoutingModule } from './predavac-routing.module';
import { ProfileComponent } from './components/profile/profile.component';
import { FormsModule } from '@angular/forms';
import { AngularMaterialModule } from '../angular-material/angular-material.module';
import { ObavestenjeComponent } from './components/obavestenje/obavestenje.component';
import { DodajTerminComponent } from './components/dodaj-termin/dodaj-termin.component';
import { ElektronskiPotpisComponent } from './components/elektronski-potpis/elektronski-potpis.component';
import { UnesiOcenuComponent } from './components/unesi-ocenu/unesi-ocenu.component';
import { UnesiBodoveComponent } from './components/unesi-bodove/unesi-bodove.component';
import { Prijaveomponent } from './components/prijave/prijave.component';

@NgModule({
  declarations: [
    ProfileComponent,
    ObavestenjeComponent,
    DodajTerminComponent,
    ElektronskiPotpisComponent,
    UnesiOcenuComponent,
    UnesiBodoveComponent,
    Prijaveomponent,
  ],
  imports: [
    CommonModule,
    PredavacRoutingModule,
    AngularMaterialModule,
    FormsModule,
  ],
})
export class PredavacModule {}
