import { PaginaNaoEncontradaComponent } from './message/pagina-nao-encontrada.component';
import { NaoAutorizadoComponent } from './message/nao-autorizado.component';

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: 'dashboard', loadChildren: () => import('./modules/dashboard/dashboard.module').then(m => m.DashboardModule)},

  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'nao-autorizado', component: NaoAutorizadoComponent },
  { path: 'pagina-nao-encontrada', component: PaginaNaoEncontradaComponent },
 /*  { path: '**', redirectTo: 'pagina-nao-encontrada' } */
];


@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }