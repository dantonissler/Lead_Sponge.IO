import { SegurancaModule } from './usuarios/modules/seguranca.module';
import { CoreModule } from './core/modules/core.module';
import { AppRoutingModule } from './app-routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { ContatoCadastroTelefoneComponent } from './contatos/components/contato-cadastro-telefone/contato-cadastro-telefone.component';
import { ContatoCadastroEmailComponent } from './contatos/components/contato-cadastro-email/contato-cadastro-email.component';
import { ContatoCadastroComponent } from './contatos/components/contato-cadastro/contato-cadastro.component';
import { ContatoPesquisaComponent } from './contatos/components/contato-pesquisa/contato-pesquisa.component';

@NgModule({
  declarations: [
    AppComponent,
    ContatoCadastroTelefoneComponent,
    ContatoCadastroEmailComponent,
    ContatoCadastroComponent,
    ContatoPesquisaComponent,

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
