import { TarefasCadastroComponent } from './../components/tarefas-cadastro/tarefas-cadastro.component';
import { TarefasPesquisaComponent } from './../components/tarefas-pesquisa/tarefas-pesquisa.component';
import { AuthGuard } from './../../usuarios/auth.guard';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';



const routes: Routes = [
  {
    path: '',
    component: TarefasPesquisaComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ROLE_PESQUISAR_USUARIO'] }
  },
  {
    path: 'novo',
    component: TarefasCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ROLE_CADASTRAR_USUARIO'] }
  },
  {
    path: ':id',
    component: TarefasCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ROLE_CADASTRAR_USUARIO'] }
  }
]

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class TarefasRoutingModule { }
