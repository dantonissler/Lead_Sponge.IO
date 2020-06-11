import { MotivoPerdaPesquisaComponent } from './../components/motivo-perda-pesquisa/motivo-perda-pesquisa.component';
import { CheckboxModule } from 'primeng/checkbox';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { TooltipModule } from 'primeng/tooltip';
import { InputMaskModule } from 'primeng/inputmask';
import { PanelModule } from 'primeng/panel';
import { DialogModule } from 'primeng/dialog';
import { DropdownModule } from 'primeng/dropdown';
import { SharedModule } from './../../core/modules/shared.module';
import { MultiSelectModule } from 'primeng/multiselect';
import { MotivoPerdaRoutingModule } from './motivo-perda-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MotivoPerdaCadastroComponent } from '../components/motivo-perda-cadastro/motivo-perda-cadastro.component';



@NgModule({
  declarations: [
    MotivoPerdaCadastroComponent,
    MotivoPerdaPesquisaComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,

    CheckboxModule,
    InputTextModule,
    ButtonModule,
    TableModule,
    TooltipModule,
    InputMaskModule,
    PanelModule,
    DialogModule,
    DropdownModule,
    MultiSelectModule,
    
    SharedModule,
    MotivoPerdaRoutingModule
  ]
})
export class MotivoPerdaModule { }
