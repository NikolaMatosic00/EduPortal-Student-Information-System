import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { AutentificationRoutingModule } from './autentification-routing.module';
import { LoginComponent } from './components/login/login.component';
import { AngularMaterialModule } from '../angular-material/angular-material.module';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { ChangeEmailComponent } from './components/change-email/change-email.component';
@NgModule({
  declarations: [LoginComponent, ChangePasswordComponent, ChangeEmailComponent],
  imports: [
    CommonModule,
    AutentificationRoutingModule,
    FormsModule,
    AngularMaterialModule,
  ],
})
export class AutentificationModule {}
