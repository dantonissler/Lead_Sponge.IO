import { SegurancaModule } from './modules/seguranca/seguranca.module';
import { CoreModule } from './modules/core/core.module';
import { AppRoutingModule } from './app-routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';


import { CadastroComponent } from './components/usuarios/cadastro/cadastro.component';
import { ListarComponent } from './components/usuarios/listar/listar.component';
import { UsuarioListarComponent } from './components/usuarios/usuario-listar/usuario-listar.component';
import { UsuarioCadastroComponent } from './components/usuarios/usuario-cadastro/usuario-cadastro.component';
import { UsuarioCadastrarComponent } from './components/usuarios/usuario-cadastrar/usuario-cadastrar.component';

@NgModule({
  declarations: [
    AppComponent,
    CadastroComponent,
    ListarComponent,
    UsuarioListarComponent,
    UsuarioCadastroComponent,
    UsuarioCadastrarComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    CoreModule,
    AppRoutingModule,
    SegurancaModule,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
