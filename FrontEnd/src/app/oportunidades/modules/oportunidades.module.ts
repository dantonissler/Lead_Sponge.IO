import { OportunidadesPesquisaComponent } from './../components/oportunidades-pesquisa/oportunidades-pesquisa.component';
import { OportunidadesCadastroComponent } from './../components/oportunidades-cadastro/oportunidades-cadastro.component';
import { OportunidadesRoutingModule } from './oportunidades-routing.module';
import { SharedModule } from './../../core/modules/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';



@NgModule({
  declarations: [
    OportunidadesCadastroComponent,
    OportunidadesPesquisaComponent
  ],
  imports: [
    CommonModule,

    SharedModule,
    OportunidadesRoutingModule
  ]
})
export class OportunidadesModule { }
