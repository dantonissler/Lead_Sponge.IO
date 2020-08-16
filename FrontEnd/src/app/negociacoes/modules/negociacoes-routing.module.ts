import { NegociacoesCadastroComponent } from '../components/negociacoes-cadastro/negociacoes-cadastro.component';
import { AuthGuard } from '../../usuarios/auth.guard';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { NegociacoesPesquisaComponent } from '../components/negociacoes-pesquisa/negociacoes-pesquisa.component';
import { NegociacaoesDetalhaComponent } from '../components/negociacoes-detalha/negociacaoes-detalha.component';

const routes: Routes = [
    {
        path: '',
        component: NegociacoesPesquisaComponent,
        canActivate: [AuthGuard],
        data: { roles: ['PESQUISAR_NEGOCIACAO'] }
    },
    {
        path: 'novo',
        component: NegociacoesCadastroComponent,
        canActivate: [AuthGuard],
        data: { roles: ['CADASTRAR_NEGOCIACAO'] }
    },
    {
        path: ':id',
        component: NegociacoesCadastroComponent,
        canActivate: [AuthGuard],
        data: { roles: ['CADASTRAR_NEGOCIACAO'] }
    },
    {
        path: 'detalhar/:id',
        component: NegociacaoesDetalhaComponent,
        canActivate: [AuthGuard],
        data: { roles: ['PESQUISAR_NEGOCIACAO'] }
    },
];

@NgModule({
    declarations: [],
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [RouterModule]
})
export class NegociacoesRoutingModule { }
