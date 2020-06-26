import { InputMaskModule } from 'primeng/inputmask';
import { SelectButtonModule } from 'primeng/selectbutton';
import { DropdownModule } from 'primeng/dropdown';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { TarefasPesquisaComponent } from './../components/tarefas-pesquisa/tarefas-pesquisa.component';
import { TarefasCadastroComponent } from './../components/tarefas-cadastro/tarefas-cadastro.component';
import { TarefasRoutingModule } from './tarefas-routing.module';
import { SharedModule } from './../../core/modules/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CalendarModule } from 'primeng/calendar';



@NgModule({
  declarations: [
    TarefasCadastroComponent,
    TarefasPesquisaComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,

    InputTextModule,
    ButtonModule,
    TableModule,
    DropdownModule,
    SelectButtonModule,
    CalendarModule,
    
    SharedModule,
    TarefasRoutingModule
  ]
})
export class TarefasModule { }
