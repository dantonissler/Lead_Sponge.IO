import { EstagioNegociacaoService } from './../../../estagioNegociacao/services/estagio-negociacao.service';
import { MenuItem } from 'primeng/api/menuitem';
import { Title } from '@angular/platform-browser';
import { ConfirmationService, MessageService, LazyLoadEvent } from 'primeng/api';
import { ErrorHandlerService } from './../../../core/error-handler.service';
import { NegociacoesFiltro, NegociacoesService } from './../../services/negociacoes.service';
import { Component, OnInit, ViewChild, ViewEncapsulation } from '@angular/core';
import { Negociacao } from '../../models/negociacao.models';
import { Table } from 'primeng/table';

@Component({
    selector: 'app-negociacoes-pesquisa',
    templateUrl: './negociacoes-pesquisa.component.html',
    styleUrls: ['./negociacoes-pesquisa.component.scss'],
})
export class NegociacoesPesquisaComponent implements OnInit {

    totalRegistros = 0;
    filtro = new NegociacoesFiltro();
    negociacoes: Negociacao[];
    items: MenuItem[];
    selectedNegociacao: Negociacao[];
    loading: boolean = true;
    @ViewChild('dt') table: Table;

    constructor(
        private negociacoesService: NegociacoesService,
        private errorHandler: ErrorHandlerService,
        private confirmation: ConfirmationService,
        private messageService: MessageService,
        private title: Title,
    ) { }

    ngOnInit(): void {
        this.title.setTitle('Pesquisa de Negociação');
    }

    pesquisar(pagina = 0) {
        this.filtro.pagina = pagina;
        this.negociacoesService.pesquisar(this.filtro)
            .then(resultado => {
                this.totalRegistros = resultado.total;
                this.negociacoes = resultado.negociacoes;
                this.loading = false;
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    aoMudarPagina(event: LazyLoadEvent) {
        const pagina = event.first / event.rows;
        this.pesquisar(pagina);
    }

    confirmarExclusao(negociacao: any) {
        this.confirmation.confirm({
            message: 'Tem certeza que deseja excluir?',
            accept: () => {
                this.excluir(negociacao);
            }
        });
    }

    excluir(negociacao: any) {
        this.negociacoesService.excluir(negociacao.id)
            .then(() => {
                if (this.table.first === 0) {
                    this.pesquisar();
                } else {
                    this.table.first = 0;
                    this.pesquisar();
                }
                this.messageService.add({ severity: 'success', detail: 'Negociação excluída com sucesso!' });
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    alternarAvaliacao(negociacoes: any): void {
        const novoavaliacao = negociacoes.avaliacao;
        this.negociacoesService.mudarAvaliacao(negociacoes.id, novoavaliacao)
            .then(() => {
                const acao = novoavaliacao;
                negociacoes.avaliacao = novoavaliacao;
                this.messageService.add({ severity: 'success', detail: `Avaliação da negociação igual à ${acao} alterado com sucesso!` });
            })
            .catch(erro => this.errorHandler.handle(erro));
    }
}