import { ProdutosPesquisaComponent } from './../components/produtos-pesquisa/produtos-pesquisa.component';
import { ProdutosCadastroComponent } from './../components/produtos-cadastro/produtos-cadastro.component';
import { ProdutosRoutingModule } from './produtos-routing.module';
import { SharedModule } from './../../core/modules/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

@NgModule({
  declarations: [
    ProdutosCadastroComponent,
    ProdutosPesquisaComponent
  ],
  imports: [
    CommonModule,

    SharedModule,
    ProdutosRoutingModule
  ]
})
export class ProdutosModule { }
