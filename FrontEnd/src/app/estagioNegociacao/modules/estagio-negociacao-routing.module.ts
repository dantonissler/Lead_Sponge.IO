import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './../../usuarios/auth.guard';
import { EstagioNegociacaoPesquisaComponent } from './../components/estagio-negociacao-pesquisa/estagio-negociacao-pesquisa.component';
import { NgModule } from '@angular/core';
import { EstagioNegociacaoCadastroComponent } from '../components/estagio-negociacao-cadastro/estagio-negociacao-cadastro.component';

const routes: Routes = [
  {
    path: '',
    component: EstagioNegociacaoPesquisaComponent,
    canActivate: [AuthGuard],
    data: { roles: ['PESQUISAR_FONTE'] }
  },
  {
    path: 'novo',
    component: EstagioNegociacaoCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['CADASTRAR_FONTE'] }
  },
  {
    path: ':id',
    component: EstagioNegociacaoCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['CADASTRAR_FONTE'] }
  }
]

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class EstagioNegociacaoRoutingModule { }
