import { NegociacoesCadastroComponent } from '../components/negociacoes-cadastro/negociacoes-cadastro.component';
import { AuthGuard } from '../../usuarios/auth.guard';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { NegociacoesPesquisaComponent } from '../components/negociacoes-pesquisa/negociacoes-pesquisa.component';

const routes: Routes = [
  {
    path: '',
    component: NegociacoesPesquisaComponent,
    canActivate: [AuthGuard],
    data: { roles: ['CADASTRAR_NEGOCIACAO'] }
  },
  {
    path: 'novo',
    component: NegociacoesCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['CADASTRAR_NEGOCIACAO'] }
  },
  {
    path: ':id',
    component: NegociacoesCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['CADASTRAR_NEGOCIACAO'] }
  }
]

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class NegociacoesRoutingModule { }
