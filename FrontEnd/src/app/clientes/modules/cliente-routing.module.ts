import { ClienteDetalhaComponent } from './../components/cliente-detalha/cliente-detalha.component';
import { ClienteCadastroComponent } from './../components/cliente-cadastro/cliente-cadastro.component';
import { AuthGuard } from './../../usuarios/auth.guard';
import { ClientePesquisaComponent } from './../components/cliente-pesquisa/cliente-pesquisa.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

const routes: Routes = [
  {
    path: '',
    component: ClientePesquisaComponent,
    canActivate: [AuthGuard],
    data: { roles: ['PESQUISAR_CLIENTE'] }
  },
  {
    path: 'novo',
    component: ClienteCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['CADASTRAR_CLIENTE'] }
  },
  {
    path: ':id',
    component: ClienteCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['CADASTRAR_CLIENTE'] }
  },
  {
    path: 'detalhar/:id',
    component: ClienteDetalhaComponent,
    canActivate: [AuthGuard],
    data: { roles: ['PESQUISAR_CLIENTE'] }
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class ClienteRoutingModule { }
