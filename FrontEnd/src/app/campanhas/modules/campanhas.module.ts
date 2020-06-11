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
import { CampanhasPesquisaComponent } from './../components/campanhas-pesquisa/campanhas-pesquisa.component';
import { CampanhasCadastroComponent } from './../components/campanhas-cadastro/campanhas-cadastro.component';
import { SharedModule } from './../../core/modules/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CampanhasRoutingModule } from './campanhas-routing.module';
import { ReactiveFormsModule , FormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    CampanhasCadastroComponent,
    CampanhasPesquisaComponent
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
    CampanhasRoutingModule
  ]
})
export class CampanhasModule { }
