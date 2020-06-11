import { UsuarioService } from './../../usuarios/services/usuario.service';
import { ErrorHandlerService } from './../../core/error-handler.service';
import { AuthService } from './../../usuarios/services/auth.service';
import { ConfiguracoesRoutingModule } from './configuracoes-routing.module';
import { ConfiguracoesComponent } from './../components/configuracoes/configuracoes.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';



@NgModule({
  declarations: [
    ConfiguracoesComponent
  ],
  imports: [
    CommonModule,
    ConfiguracoesRoutingModule
  ],
  providers: [
    AuthService,
    UsuarioService,
    ErrorHandlerService,
  ],
  exports: [
    
   ],
})
export class ConfiguracoesModule { }
