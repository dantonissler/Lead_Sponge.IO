import { ContatoRoutingModule } from './contatos-routing.module';
import { ContatoPesquisaComponent } from './../components/contato-pesquisa/contato-pesquisa.component';
import { ContatoCadastroComponent } from './../components/contato-cadastro/contato-cadastro.component';
import { SharedModule } from './../../core/modules/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';



@NgModule({
  declarations: [
    ContatoCadastroComponent,
    ContatoPesquisaComponent
  ],
  imports: [
    CommonModule,
    
    SharedModule,
    ContatoRoutingModule
  ]
})
export class ContatoModule { }
