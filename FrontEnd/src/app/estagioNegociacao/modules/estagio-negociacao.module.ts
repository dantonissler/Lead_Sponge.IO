import { EstagioNegociacaoRoutingModule } from './estagio-negociacao-routing.module';
import { SharedModule } from './../../core/modules/shared.module';
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
import { EstagioNegociacaoPesquisaComponent } from './../components/estagio-negociacao-pesquisa/estagio-negociacao-pesquisa.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EstagioNegociacaoCadastroComponent } from '../components/estagio-negociacao-cadastro/estagio-negociacao-cadastro.component';



@NgModule({
  declarations: [
    EstagioNegociacaoCadastroComponent,
    EstagioNegociacaoPesquisaComponent
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
    EstagioNegociacaoRoutingModule
  ]
})
export class EstagioNegociacaoModule { }
