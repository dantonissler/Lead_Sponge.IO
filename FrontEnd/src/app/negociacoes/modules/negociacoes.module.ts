import { StepsExtendedModule } from '../components/funil-de-vendas/p-steps-extended';
import { FunilDeVendasComponent } from './../components/funil-de-vendas/funil-de-vendas.component';
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
import {RatingModule} from 'primeng/rating';
import {StepsModule} from 'primeng/steps';
import { HttpClientModule } from '@angular/common/http';
import {ContextMenuModule} from 'primeng/contextmenu';
import {ToastModule} from 'primeng/toast';
import {ProgressBarModule} from 'primeng/progressbar';

@NgModule({
  declarations: [
    NegociacoesCadastroComponent,
    NegociacoesPesquisaComponent,
    FunilDeVendasComponent,
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
    RatingModule,
    StepsModule,
    
		ContextMenuModule,
		ToastModule,
    ProgressBarModule,
    HttpClientModule,
    StepsExtendedModule,

    SharedModule,
    NegociacoesRoutingModule
  ]
})
export class NegociacoesModule { }
