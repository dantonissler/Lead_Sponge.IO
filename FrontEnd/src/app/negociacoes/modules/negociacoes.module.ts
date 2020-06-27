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
import { NegociacoesPesquisaComponent } from '../components/negociacoes-pesquisa/negociacoes-pesquisa.component';
import { NegociacoesCadastroComponent } from '../components/negociacoes-cadastro/negociacoes-cadastro.component';
import { NegociacoesRoutingModule } from './negociacoes-routing.module';
import { SharedModule } from '../../core/modules/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    NegociacoesCadastroComponent,
    NegociacoesPesquisaComponent
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
    NegociacoesRoutingModule
  ]
})
export class NegociacoesModule { }
