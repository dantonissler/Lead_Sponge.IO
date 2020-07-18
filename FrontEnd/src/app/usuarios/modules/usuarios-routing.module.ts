import { UsuarioDetalharComponent } from './../components/usuario-detalhar/usuario-detalhar.component';
import { UsuarioCadastrarComponent } from '../components/usuario-cadastrar/usuario-cadastrar.component';
import { UsuarioListarComponent } from '../components/usuario-listar/usuario-listar.component';
import { AuthGuard } from '../auth.guard';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    component: UsuarioListarComponent,
    canActivate: [AuthGuard],
    data: { roles: ['PESQUISAR_USUARIO'] }
  },
  {
    path: 'detalhar/:id',
    component: UsuarioDetalharComponent,
    canActivate: [AuthGuard],
    data: { roles: ['PESQUISAR_USUARIO'] }
  },
  {
    path: 'novo',
    component: UsuarioCadastrarComponent,
    canActivate: [AuthGuard],
    data: { roles: ['CADASTRAR_USUARIO'] }
  },
  {
    path: ':id',
    component: UsuarioCadastrarComponent,
    canActivate: [AuthGuard],
    data: { roles: ['CADASTRAR_USUARIO'] }
  }
]

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class UsuariosRoutingModule { }
