import { MotivoPerdaRoutingModule } from './motivoPerda/modules/motivo-perda-routing.module';
import { PaginaNaoEncontradaComponent } from './core/components/pagina-nao-encontrada.component';
import { NaoAutorizadoComponent } from './core/components/nao-autorizado.component';

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: 'dashboard', loadChildren: () => import('./dashboard/dashboard.module').then(m => m.DashboardModule)},
  { path: 'oportunidades', loadChildren: () => import('./negociacoes/modules/negociacoes.module').then(m => m.NegociacoesModule) },
  { path: 'clientes', loadChildren: () => import('./clientes/modules/cliente.module').then(m => m.ClienteModule) },
  { path: 'tarefas', loadChildren: () => import('./tarefas/modules/tarefas.module').then(m => m.TarefasModule) },
  { path: 'produtos', loadChildren: () => import('./produtos/modules/produtos.module').then(m => m.ProdutosModule) },
  { path: 'contatos', loadChildren: () => import('./contatos/modules/contatos.module').then(m => m.ContatoModule) },
  { path: 'campanhas', loadChildren: () => import('./campanhas/modules/campanhas.module').then(m => m.CampanhasModule) },
  { path: 'estagios', loadChildren: () => import('./estagioNegociacao/modules/estagio-negociacao.module').then(m => m.EstagioNegociacaoModule) },
  { path: 'segmentos', loadChildren: () => import('./segmentos/modules/segmentos.module').then(m => m.SegmentosModule) },
  { path: 'fontes', loadChildren: () => import('./fonteNegociacao/modules/fontes.module').then(m => m.FontesModule) },
  { path: 'usuarios', loadChildren: () => import('./usuarios/modules/usuarios.module').then(m => m.UsuariosModule) },
  { path: 'configuracoes', loadChildren: () => import('./configuracoes/modules/configuracoes.module').then(m => m.ConfiguracoesModule) },
  { path: 'motivoperda', loadChildren: () => import('./motivoPerda/modules/motivo-perda.module').then(m => m.MotivoPerdaModule) },

  
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'nao-autorizado', component: NaoAutorizadoComponent },
  { path: 'pagina-nao-encontrada', component: PaginaNaoEncontradaComponent },
  /* { path: '**', redirectTo: 'pagina-nao-encontrada' } */
];


@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }