import { ContatoCadastroComponent } from './../components/contato-cadastro/contato-cadastro.component';
import { ContatoPesquisaComponent } from './../components/contato-pesquisa/contato-pesquisa.component';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './../../usuarios/auth.guard';
import { NgModule } from '@angular/core';

const routes: Routes = [
  {
    path: '',
    component: ContatoPesquisaComponent,
    canActivate: [AuthGuard],
    data: { roles: ['PESQUISAR_CONTATO'] }
  },
  {
    path: 'novo',
    component: ContatoCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['CADASTRAR_CONTATO'] }
  },
  {
    path: ':id',
    component: ContatoCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['CADASTRAR_CONTATO'] }
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
