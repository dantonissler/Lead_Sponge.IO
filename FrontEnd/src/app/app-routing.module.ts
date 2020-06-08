import { PaginaNaoEncontradaComponent } from './core/components/pagina-nao-encontrada.component';
import { NaoAutorizadoComponent } from './core/components/nao-autorizado.component';

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: 'dashboard', loadChildren: () => import('./dashboard/dashboard.module').then(m => m.DashboardModule)},
  { path: 'oportunidades', loadChildren: () => import('./oportunidades/modules/oportunidades.module').then(m => m.OportunidadesModule) },
  { path: 'clientes', loadChildren: () => import('./clientes/modules/cliente.module').then(m => m.ClienteModule) },
  { path: 'tarefas', loadChildren: () => import('./tarefas/modules/tarefas.module').then(m => m.TarefasModule) },
  { path: 'produtos', loadChildren: () => import('./produtos/modules/produtos.module').then(m => m.ProdutosModule) },
  { path: 'contatos', loadChildren: () => import('./contatos/modules/contatos.module').then(m => m.ContatoModule) },
  { path: 'campanhas', loadChildren: () => import('./campanhas/modules/campanhas.module').then(m => m.CampanhasModule) },
  { path: 'fontes', loadChildren: () => import('./fontes/modules/fontes.module').then(m => m.FontesModule) },
  { path: 'usuarios', loadChildren: () => import('./usuarios/modules/usuarios.module').then(m => m.UsuariosModule) },

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