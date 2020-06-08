import { ContatoCadastroComponent } from './../components/contato-cadastro/contato-cadastro.component';
import { ContatoPesquisaComponent } from './../components/contato-pesquisa/contato-pesquisa.component';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './../../usuarios/auth.guard';
import { ClienteCadastroComponent } from './../../clientes/components/cliente-cadastro/cliente-cadastro.component';
import { ClientePesquisaComponent } from './../../clientes/components/cliente-pesquisa/cliente-pesquisa.component';
import { NgModule } from '@angular/core';

const routes: Routes = [
  {
    path: '',
    component: ContatoPesquisaComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ROLE_PESQUISAR_CLIENTE'] }
  },
  {
    path: 'novo',
    component: ContatoCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ROLE_CADASTRAR_CLIENTE'] }
  },
  {
    path: ':id',
    component: ContatoCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ROLE_CADASTRAR_CLIENTE'] }
  }
]

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class ContatoRoutingModule { }
