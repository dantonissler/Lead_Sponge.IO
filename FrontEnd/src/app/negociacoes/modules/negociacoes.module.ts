import { NegociacoesPesquisaComponent } from '../components/negociacoes-pesquisa/negociacoes-pesquisa.component';
import { NegociacoesCadastroComponent } from '../components/negociacoes-cadastro/negociacoes-cadastro.component';
import { NegociacoesRoutingModule } from './negociacoes-routing.module';
import { SharedModule } from '../../core/modules/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

@NgModule({
  declarations: [
    NegociacoesCadastroComponent,
    NegociacoesPesquisaComponent
  ],
  imports: [
    CommonModule,

    SharedModule,
    NegociacoesRoutingModule
  ]
})
export class NegociacoesModule { }
