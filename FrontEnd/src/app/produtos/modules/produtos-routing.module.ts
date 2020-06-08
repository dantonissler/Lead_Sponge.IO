import { ProdutosPesquisaComponent } from './../components/produtos-pesquisa/produtos-pesquisa.component';
import { ProdutosCadastroComponent } from './../components/produtos-cadastro/produtos-cadastro.component';
import { AuthGuard } from './../../usuarios/auth.guard';
import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';

const routes: Routes = [
  {
    path: '',
    component: ProdutosPesquisaComponent,
    canActivate: [AuthGuard],
    data: { roles: ['PESQUISAR_PRODUTO'] }
  },
  {
    path: 'novo',
    component: ProdutosCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['CADASTRAR_PRODUTO'] }
  },
  {
    path: ':id',
    component: ProdutosCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['CADASTRAR_PRODUTO'] }
  }
]

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class ProdutosRoutingModule { }
