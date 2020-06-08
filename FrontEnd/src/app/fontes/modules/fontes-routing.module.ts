import { FontesCadastroComponent } from './../components/fontes-cadastro/fontes-cadastro.component';
import { FontesPesquisaComponent } from './../components/fontes-pesquisa/fontes-pesquisa.component';
import { AuthGuard } from './../../usuarios/auth.guard';
import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';

const routes: Routes = [
  {
    path: '',
    component: FontesPesquisaComponent,
    canActivate: [AuthGuard],
    data: { roles: ['PESQUISAR_FONTE'] }
  },
  {
    path: 'novo',
    component: FontesCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['CADASTRAR_FONTE'] }
  },
  {
    path: ':id',
    component: FontesCadastroComponent,
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
export class FontesRoutingModule { }
