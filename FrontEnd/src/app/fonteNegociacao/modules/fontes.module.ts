import { MultiSelectModule } from 'primeng/multiselect';
import { DropdownModule } from 'primeng/dropdown';
import { DialogModule } from 'primeng/dialog';
import { PanelModule } from 'primeng/panel';
import { InputMaskModule } from 'primeng/inputmask';
import { TooltipModule } from 'primeng/tooltip';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { CheckboxModule } from 'primeng/checkbox';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FontesPesquisaComponent } from '../components/fonteNegociacao-pesquisa/fonteNegociacao-pesquisa.component';
import { FontesCadastroComponent } from '../components/fonteNegociacao-cadastro/fonteNegociacao-cadastro.component';
import { FontesRoutingModule } from './fontes-routing.module';
import { SharedModule } from '../../core/modules/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';



@NgModule({
  declarations: [
    FontesCadastroComponent,
    FontesPesquisaComponent
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
    FontesRoutingModule
  ]
})
export class FontesModule { }
