import { SegmentosRoutingModule } from './segmentos-routing.module';
import { SharedModule } from './../../core/modules/shared.module';
import { ButtonModule } from 'primeng/button';
import { MultiSelectModule } from 'primeng/multiselect';
import { DropdownModule } from 'primeng/dropdown';
import { DialogModule } from 'primeng/dialog';
import { PanelModule } from 'primeng/panel';
import { InputMaskModule } from 'primeng/inputmask';
import { TooltipModule } from 'primeng/tooltip';
import { TableModule } from 'primeng/table';
import { InputTextModule } from 'primeng/inputtext';
import { CheckboxModule } from 'primeng/checkbox';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SegmentosPesquisaComponent } from './../components/segmentos-pesquisa/segmentos-pesquisa.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SegmentosCadastroComponent } from '../components/segmentos-cadastro/segmentos-cadastro.component';

@NgModule({
  declarations: [
    SegmentosPesquisaComponent,
    SegmentosCadastroComponent
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
    SegmentosRoutingModule,
  ]
})
export class SegmentosModule { }
