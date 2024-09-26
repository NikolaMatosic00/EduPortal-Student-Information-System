import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IstorijaPolaganjaComponent } from './components/istorija-polaganja/istorija-polaganja.component';
import { PredmetiPolaganjeComponent } from './components/predmeti polaganje/predmeti-polaganje.component';
import { PredmetiComponent } from './components/predmeti/predmeti.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RasporedComponent } from './components/raspored/raspored.component';
import { UplateComponent } from './components/uplate/uplate.component';
import { PrijavaIspitaComponent } from './components/prijava-ispita/prijava-ispita.component';

const routes: Routes = [
  { path: '', component: ProfileComponent },
  { path: 'uplate', component: UplateComponent },
  { path: 'predmeti', component: PredmetiComponent },
  { path: 'predmetiZaPolaganje', component: PredmetiPolaganjeComponent },
  { path: 'raspored', component: RasporedComponent },
  { path: 'istorijaPolaganja', component: IstorijaPolaganjaComponent },
  { path: 'prijavaIspita', component: PrijavaIspitaComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class StudentRoutingModule {}
