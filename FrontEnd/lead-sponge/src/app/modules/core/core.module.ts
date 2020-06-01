import { FooterComponent } from '../../components/core/footer/footer.component';
import { Title } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { NgModule, LOCALE_ID } from '@angular/core';
import { CommonModule, registerLocaleData } from '@angular/common';
import localePt from '@angular/common/locales/pt';

import { ConfirmationService, MessageService } from 'primeng/api';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ToastModule } from 'primeng/toast';
import { JwtHelperService } from '@auth0/angular-jwt';

import { AuthService } from '../../services/auth.service';
import { ErrorHandlerService } from '../../services/error-handler.service';
import { NaoAutorizadoComponent } from '../../message/nao-autorizado.component';
import { PaginaNaoEncontradaComponent } from '../../message/pagina-nao-encontrada.component';
import { MoneyHttp } from '../../seguranca/money-http';
import { NavbarComponent } from '../../components/core/navbar/navbar.component';
import {MenubarModule} from 'primeng/menubar';
import {SidebarModule} from 'primeng/sidebar';
import {SlideMenuModule} from 'primeng/slidemenu';

registerLocaleData(localePt);

@NgModule({
  imports: [
    SlideMenuModule,
    SidebarModule,
    MenubarModule,
    CommonModule,
    HttpClientModule,
    RouterModule,
    ToastModule,
    ConfirmDialogModule,
  ],
  declarations: [
    FooterComponent,
    NavbarComponent,
    PaginaNaoEncontradaComponent,
    NaoAutorizadoComponent
  ],
  exports: [    
    FooterComponent,
    NavbarComponent,
    ToastModule,
    ConfirmDialogModule
  ],
  providers: [
    /* LancamentoService,
    PessoaService,
    CategoriaService,
    DashboardService,
    RelatoriosService, */
    ErrorHandlerService,
    AuthService,
    MoneyHttp,

    ConfirmationService,
    MessageService,
    JwtHelperService,
    Title,
    { provide: LOCALE_ID, useValue: 'pt' }
  ]
})
export class CoreModule { }
