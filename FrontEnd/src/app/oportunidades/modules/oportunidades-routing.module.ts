import { OportunidadesCadastroComponent } from './../components/oportunidades-cadastro/oportunidades-cadastro.component';
import { AuthGuard } from './../../usuarios/auth.guard';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { OportunidadesPesquisaComponent } from '../components/oportunidades-pesquisa/oportunidades-pesquisa.component';

const routes: Routes = [
  {
    path: '',
    component: OportunidadesPesquisaComponent,
    canActivate: [AuthGuard],
    data: { roles: ['CADASTRAR_OPORTUNIDADE'] }
  },
  {
    path: 'novo',
    component: OportunidadesCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['CADASTRAR_OPORTUNIDADE'] }
  },
  {
    path: ':id',
    component: OportunidadesCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['CADASTRAR_OPORTUNIDADE'] }
  }
]

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class OportunidadesRoutingModule { }
