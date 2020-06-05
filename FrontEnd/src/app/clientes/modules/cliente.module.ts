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
    CommonModule
  ]
})
export class ClienteModule { }
