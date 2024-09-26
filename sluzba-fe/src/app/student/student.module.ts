import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StudentRoutingModule } from './student-routing.module';
import { ProfileComponent } from './components/profile/profile.component';
import { AngularMaterialModule } from '../angular-material/angular-material.module';
import { FormsModule } from '@angular/forms';
import { UplateComponent } from './components/uplate/uplate.component';
import { PredmetiComponent } from './components/predmeti/predmeti.component';
import { PredmetiPolaganjeComponent } from './components/predmeti polaganje/predmeti-polaganje.component';
import { RasporedComponent } from './components/raspored/raspored.component';
import { IstorijaPolaganjaComponent } from './components/istorija-polaganja/istorija-polaganja.component';
import { PrijavaIspitaComponent } from './components/prijava-ispita/prijava-ispita.component';

@NgModule({
  declarations: [
    ProfileComponent,
    UplateComponent,
    PredmetiComponent,
    PredmetiPolaganjeComponent,
    RasporedComponent,
    IstorijaPolaganjaComponent,
    PrijavaIspitaComponent,
  ],
  imports: [
    CommonModule,
    StudentRoutingModule,
    AngularMaterialModule,
    FormsModule,
  ],
})
export class StudentModule {}
