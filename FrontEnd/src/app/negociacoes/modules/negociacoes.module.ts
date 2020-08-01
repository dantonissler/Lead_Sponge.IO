import { InputNumberModule } from 'primeng/inputnumber';
import { CalendarModule } from 'primeng/calendar';
import { TarefasComponent } from './../components/negociacoes-detalha/tarefas/tarefas.component';
import { ProdutosComponent } from './../components/negociacoes-detalha/produtos/produtos.component';
import { HistoricoComponent } from './../components/negociacoes-detalha/historico/historico.component';
import { ContatosComponent } from './../components/negociacoes-detalha/contatos/contatos.component';
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
import {TabMenuModule} from 'primeng/tabmenu';
import { NegociacaoesDetalhaComponent } from '../components/negociacoes-detalha/negociacaoes-detalha.component';

@NgModule({
  declarations: [
    NegociacoesCadastroComponent,
    NegociacoesPesquisaComponent,
    FunilDeVendasComponent,
    NegociacaoesDetalhaComponent,
    ContatosComponent,
    HistoricoComponent,
    ProdutosComponent,
    TarefasComponent
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
    TabMenuModule,
		ContextMenuModule,
		ToastModule,
    ProgressBarModule,
    HttpClientModule,
    StepsExtendedModule,
    CalendarModule,
    InputNumberModule,

    SharedModule,
    NegociacoesRoutingModule
  ]
})
export class NegociacoesModule { }
