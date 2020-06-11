import { SegmentosPesquisaComponent } from './../components/segmentos-pesquisa/segmentos-pesquisa.component';
import { AuthGuard } from './../../usuarios/auth.guard';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { SegmentosCadastroComponent } from '../components/segmentos-cadastro/segmentos-cadastro.component';

const routes: Routes = [
  {
    path: '',
    component: SegmentosPesquisaComponent,
    canActivate: [AuthGuard],
    data: { roles: ['PESQUISAR_SEGMENTO'] }
  },
  {
    path: 'novo',
    component: SegmentosCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['CADASTRAR_SEGMENTO'] }
  },
  {
    path: ':id',
    component: SegmentosCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['CADASTRAR_SEGMENTO'] }
  }
]

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class SegmentosRoutingModule { }
