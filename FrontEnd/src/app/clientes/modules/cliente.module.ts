import { ClienteRoutingModule } from './cliente-routing.module';
import { SharedModule } from './../../core/modules/shared.module';
import { ClientePesquisaComponent } from './../components/cliente-pesquisa/cliente-pesquisa.component';
import { ClienteCadastroComponent } from './../components/cliente-cadastro/cliente-cadastro.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';



@NgModule({
  declarations: [
    ClienteCadastroComponent,
    ClientePesquisaComponent
  ],
  imports: [
    CommonModule,

    SharedModule,
    ClienteRoutingModule
  ]
})
export class ClienteModule { }
