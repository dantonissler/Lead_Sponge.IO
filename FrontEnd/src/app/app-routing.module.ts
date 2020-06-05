import { PaginaNaoEncontradaComponent } from './core/components/pagina-nao-encontrada.component';
import { NaoAutorizadoComponent } from './core/components/nao-autorizado.component';

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: 'dashboard', loadChildren: () => import('./dashboard/dashboard.module').then(m => m.DashboardModule)},
  { path: 'usuarios', loadChildren: () => import('./usuarios/modules/usuarios.module').then(m => m.UsuariosModule) },

  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'nao-autorizado', component: NaoAutorizadoComponent },
  { path: 'pagina-nao-encontrada', component: PaginaNaoEncontradaComponent },
  { path: '*', redirectTo: 'pagina-nao-encontrada' }
];


@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }