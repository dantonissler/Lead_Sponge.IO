import { UsuarioCadastrarComponent } from './../../components/usuarios/usuario-cadastrar/usuario-cadastrar.component';
import { UsuarioListarComponent } from './../../components/usuarios/usuario-listar/usuario-listar.component';
import { AuthGuard } from './../../seguranca/auth.guard';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    component: UsuarioListarComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ROLE_PESQUISAR_USUARIO'] }
  },
  {
    path: ':codigo',
    component: UsuarioCadastrarComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ROLE_CADASTRAR_USUARIO'] }
  }
]

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class UsuariosRoutingModule { }
