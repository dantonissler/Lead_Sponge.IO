import { FontesPesquisaComponent } from './../components/fontes-pesquisa/fontes-pesquisa.component';
import { FontesCadastroComponent } from './../components/fontes-cadastro/fontes-cadastro.component';
import { FontesRoutingModule } from './fontes-routing.module';
import { SharedModule } from './../../core/modules/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';



@NgModule({
  declarations: [
    FontesCadastroComponent,
    FontesPesquisaComponent
  ],
  imports: [
    CommonModule,

    SharedModule,
    FontesRoutingModule
  ]
})
export class FontesModule { }
