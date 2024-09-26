import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DodajTerminComponent } from './components/dodaj-termin/dodaj-termin.component';
import { ElektronskiPotpisComponent } from './components/elektronski-potpis/elektronski-potpis.component';
import { ObavestenjeComponent } from './components/obavestenje/obavestenje.component';
import { Prijaveomponent } from './components/prijave/prijave.component';
import { ProfileComponent } from './components/profile/profile.component';
import { UnesiBodoveComponent } from './components/unesi-bodove/unesi-bodove.component';
import { UnesiOcenuComponent } from './components/unesi-ocenu/unesi-ocenu.component';

const routes: Routes = [
  { path: '', component: ProfileComponent },
  { path: 'postaviObavestenje', component: ObavestenjeComponent },
  { path: 'dodajTermin', component: DodajTerminComponent },
  { path: 'elektronskiPotpis', component: ElektronskiPotpisComponent },
  { path: 'unesiOcenu', component: UnesiOcenuComponent },
  { path: 'unesiBodove', component: UnesiBodoveComponent },
  { path: 'prijave', component: Prijaveomponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PredavacRoutingModule {}
