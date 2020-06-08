import { CampanhasPesquisaComponent } from './../components/campanhas-pesquisa/campanhas-pesquisa.component';
import { CampanhasCadastroComponent } from './../components/campanhas-cadastro/campanhas-cadastro.component';
import { SharedModule } from './../../core/modules/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CampanhasRoutingModule } from './campanhas-routing.module';



@NgModule({
  declarations: [
    CampanhasCadastroComponent,
    CampanhasPesquisaComponent
  ],
  imports: [
    CommonModule,
    
    SharedModule,
    CampanhasRoutingModule
  ]
})
export class CampanhasModule { }
