import { LogoutService } from './../../services/logout.service';
import { AuthGuard } from './../../seguranca/auth.guard';
import { LoginFormComponent } from './../../components/login-form/login-form.component';
import { SegurancaRoutingModule } from './seguranca-routing.module';
import { environment } from './../../../environments/environment';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { JwtModule } from '@auth0/angular-jwt';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';



export function tokenGetter() {
  return localStorage.getItem('token');
}

@NgModule({
  imports: [
    CommonModule,
    FormsModule,

    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        whitelistedDomains: environment.tokenWhitelistedDomains,
        blacklistedRoutes: environment.tokenBlacklistedRoutes
      }
    }),
    InputTextModule,
    ButtonModule,
    
    SegurancaRoutingModule
  ],
  declarations: [LoginFormComponent],
  providers: [
    AuthGuard,
    LogoutService
  ]
})
export class SegurancaModule { }
