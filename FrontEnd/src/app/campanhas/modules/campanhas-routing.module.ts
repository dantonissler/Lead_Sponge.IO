import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './../../usuarios/auth.guard';
import { CampanhasCadastroComponent } from './../components/campanhas-cadastro/campanhas-cadastro.component';
import { CampanhasPesquisaComponent } from './../components/campanhas-pesquisa/campanhas-pesquisa.component';
import { NgModule } from '@angular/core';

const routes: Routes = [
  {
    path: '',
    component: CampanhasPesquisaComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ROLE_PESQUISAR_CAMPANHA'] }
  },
  {
    path: 'novo',
    component: CampanhasCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ROLE_CADASTRAR_CAMPANHA'] }
  },
  {
    path: ':id',
    component: CampanhasCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ROLE_CADASTRAR_CAMPANHA'] }
  }
]

@NgModule({
  imports: [
    RouterModule.forChild(routes)    
  ],
  exports: [RouterModule]
})
export class CampanhasRoutingModule { }
