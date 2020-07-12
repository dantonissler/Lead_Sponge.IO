import { AuthGuard } from './../../../usuarios/auth.guard';
import { LixeiraPesquisaComponent } from './../lixeira-pesquisa/lixeira-pesquisa.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

const routes: Routes = [
  {
    path: '',
    component: LixeiraPesquisaComponent,
    canActivate: [AuthGuard],
    data: { roles: ['USER'] }
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class LixeiraRoutingModule { }
