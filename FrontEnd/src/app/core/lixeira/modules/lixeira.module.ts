import { LixeiraRoutingModule } from './lixeira-routing.module';
import { LixeiraPesquisaComponent } from './../lixeira-pesquisa/lixeira-pesquisa.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';


@NgModule({
  declarations: [
    LixeiraPesquisaComponent
  ],
  imports: [
    CommonModule,
    LixeiraRoutingModule
  ]
})
export class LixeiraModule { }
