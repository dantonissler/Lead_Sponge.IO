import { TarefasPesquisaComponent } from './../components/tarefas-pesquisa/tarefas-pesquisa.component';
import { TarefasCadastroComponent } from './../components/tarefas-cadastro/tarefas-cadastro.component';
import { TarefasRoutingModule } from './tarefas-routing.module';
import { SharedModule } from './../../core/modules/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';



@NgModule({
  declarations: [
    TarefasCadastroComponent,
    TarefasPesquisaComponent
  ],
  imports: [
    CommonModule,

    SharedModule,
    TarefasRoutingModule
  ]
})
export class TarefasModule { }
