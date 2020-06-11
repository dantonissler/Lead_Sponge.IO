import { AuthGuard } from './../../usuarios/auth.guard';
import { MotivoPerdaPesquisaComponent } from './../components/motivo-perda-pesquisa/motivo-perda-pesquisa.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { MotivoPerdaCadastroComponent } from '../components/motivo-perda-cadastro/motivo-perda-cadastro.component';

const routes: Routes = [
  {
    path: '',
    component: MotivoPerdaPesquisaComponent,
    canActivate: [AuthGuard],
    data: { roles: ['PESQUISAR_MOTIVO'] }
  },
  {
    path: 'novo',
    component: MotivoPerdaCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['CADASTRAR_MOTIVO'] }
  },
  {
    path: ':id',
    component: MotivoPerdaCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['CADASTRAR_MOTIVO'] }
  }
]

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class MotivoPerdaRoutingModule { }
