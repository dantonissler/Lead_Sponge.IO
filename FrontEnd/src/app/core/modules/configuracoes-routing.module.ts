import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './../../usuarios/auth.guard';
import { ConfiguracoesComponent } from './../components/configuracoes/configuracoes.component';
import { NgModule } from '@angular/core';

const routes: Routes = [
  {
    path: '',
    component: ConfiguracoesComponent,
    canActivate: [AuthGuard],
    data: { roles: ['USER'] }
  }
]

@NgModule({
  imports: [
    RouterModule.forChild(routes)    
  ],
  exports: [RouterModule]
})
export class ConfiguracoesRoutingModule { }
