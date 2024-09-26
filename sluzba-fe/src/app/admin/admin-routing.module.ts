import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UplateComponent } from './components/uplate/uplate.component';
import { DodajPredmetComponent } from './components/dodaj-predmet/dodaj-predmet.component';
import { DodajUloguNaPredmetComponent } from './components/dodaj-ulogu-na-predmet/dodaj-ulogu-na-predmet.component';
import { DodavanjePredavacaComponent } from './components/dodavanje-predavaca/dodavanje-predavaca.component';
import { DodavanjeStudentaComponent } from './components/dodavanje-studenta/dodavanje-studenta.component';
import { DodavanjeStudentaNaPredmetComponent } from './components/dodavanje-studenta-na-predmet/dodavanje-studenta-na-predmet.component';
import { DodavanjeSmeraComponent } from './components/dodavanje-smera/dodavanje-smera.component';
import { EvidentiranjeUplateComponent } from './components/evidentiranje-uplate/evidentiranje-uplate.component';

const routes: Routes = [
  { path: 'uplate', component: UplateComponent },
  { path: 'dodajPredmet', component: DodajPredmetComponent },
  { path: 'dodajUloguNaPredmet', component: DodajUloguNaPredmetComponent },
  { path: 'dodavanjePredavaca', component: DodavanjePredavacaComponent },
  { path: 'dodavanjeStudenta', component: DodavanjeStudentaComponent },
  {
    path: 'dodavanjeStudentaNaPredmet',
    component: DodavanjeStudentaNaPredmetComponent,
  },
  { path: 'dodavanjeSmera', component: DodavanjeSmeraComponent },
  { path: 'evidentiranjeUplate', component: EvidentiranjeUplateComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule {}
