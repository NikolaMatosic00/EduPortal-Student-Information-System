import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { AdminRoutingModule } from './admin-routing.module';
import { AngularMaterialModule } from '../angular-material/angular-material.module';
import { FormsModule } from '@angular/forms';
import { UplateComponent } from './components/uplate/uplate.component';
import { DodajPredmetComponent } from './components/dodaj-predmet/dodaj-predmet.component';
import { DodajUloguNaPredmetComponent } from './components/dodaj-ulogu-na-predmet/dodaj-ulogu-na-predmet.component';
import { DodavanjePredavacaComponent } from './components/dodavanje-predavaca/dodavanje-predavaca.component';
import { DodavanjeStudentaComponent } from './components/dodavanje-studenta/dodavanje-studenta.component';
import { DodavanjeStudentaNaPredmetComponent } from './components/dodavanje-studenta-na-predmet/dodavanje-studenta-na-predmet.component';
import { DodavanjeSmeraComponent } from './components/dodavanje-smera/dodavanje-smera.component';
import { EvidentiranjeUplateComponent } from './components/evidentiranje-uplate/evidentiranje-uplate.component';

@NgModule({
  declarations: [
    UplateComponent,
    DodajPredmetComponent,
    DodajUloguNaPredmetComponent,
    DodavanjePredavacaComponent,
    DodavanjeStudentaComponent,
    DodavanjeStudentaNaPredmetComponent,
    DodavanjeSmeraComponent,
    EvidentiranjeUplateComponent,
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    AngularMaterialModule,
    FormsModule,
    ReactiveFormsModule,
  ],
})
export class AdminModule {}
