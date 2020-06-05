import { UsuarioService } from '../../usuarios/services/usuario.service';
import { DashboardService } from '../../dashboard/dashboard.service';
import { FooterComponent } from '../components/footer/footer.component';
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
import { AuthService } from '../../usuarios/services/auth.service';
import { ErrorHandlerService } from '../error-handler.service';
import { NaoAutorizadoComponent } from '../components/nao-autorizado.component';
import { PaginaNaoEncontradaComponent } from '../components/pagina-nao-encontrada.component';
import { MoneyHttp } from '../../usuarios/money-http';
import { NavbarComponent } from '../components/navbar/navbar.component';
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
    /* 
    LancamentoService,
    PessoaService
    CategoriaService,

    RelatoriosService, */
    DashboardService,
    UsuarioService,
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
