import { ContatoCadastroTelefoneComponent } from './../components/contato-cadastro-telefone/contato-cadastro-telefone.component';
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
import { ContatoRoutingModule } from './contatos-routing.module';
import { ContatoPesquisaComponent } from './../components/contato-pesquisa/contato-pesquisa.component';
import { ContatoCadastroComponent } from './../components/contato-cadastro/contato-cadastro.component';
import { SharedModule } from './../../core/modules/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContatoCadastroEmailComponent } from '../components/contato-cadastro-email/contato-cadastro-email.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SelectButtonModule } from 'primeng/selectbutton';



@NgModule({
  declarations: [
    ContatoCadastroComponent,
    ContatoPesquisaComponent,
    ContatoCadastroTelefoneComponent,
    ContatoCadastroEmailComponent
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
    SelectButtonModule,
    
    SharedModule,
    ContatoRoutingModule
  ]
})
export class ContatoModule { }
