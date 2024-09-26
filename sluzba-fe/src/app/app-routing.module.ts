import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageNotFoundComponent } from './shared/components/page-not-found/page-not-found.component';

const routes: Routes = [
  {
    path: 'auth',
    loadChildren: () =>
      import('./autentification/autentification.module').then(
        (mod) => mod.AutentificationModule
      ),
  },
  {
    path: 'student',
    loadChildren: () =>
      import('./student/student.module').then((mod) => mod.StudentModule),
  },
  {
    path: 'predavac',
    loadChildren: () =>
      import('./predavac/predavac.module').then((mod) => mod.PredavacModule),
  },
  {
    path: 'admin',
    loadChildren: () =>
      import('./admin/admin.module').then((mod) => mod.AdminModule),
  },
  { path: 'page-not-found', component: PageNotFoundComponent },
  { path: '**', redirectTo: 'page-not-found', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule],
})
export class AppRoutingModule {}
