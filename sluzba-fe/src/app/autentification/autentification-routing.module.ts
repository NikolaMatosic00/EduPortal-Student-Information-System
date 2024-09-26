import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ChangeEmailComponent } from './components/change-email/change-email.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { LoginComponent } from './components/login/login.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'change-password', component: ChangePasswordComponent },
  { path: 'change-email', component: ChangeEmailComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AutentificationRoutingModule {}
