import { ProdutoFiltro, ProdutosService } from './../../services/produtos.service';
import { Title } from '@angular/platform-browser';
import { ErrorHandlerService } from './../../../core/error-handler.service';
import { MessageService, ConfirmationService, LazyLoadEvent } from 'primeng/api';
import { Location } from '@angular/common';

import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
    selector: 'app-produtos-pesquisa',
    templateUrl: './produtos-pesquisa.component.html',
    styleUrls: ['./produtos-pesquisa.component.scss']
})
export class ProdutosPesquisaComponent implements OnInit {

    totalRegistros = 0;
    filtro = new ProdutoFiltro();
    produtos = [];
    loading: boolean = true;
    @ViewChild('tabela', { static: true }) grid;

    constructor(
        private produtosService: ProdutosService,
        private _location: Location,
        private errorHandler: ErrorHandlerService,
        private confirmation: ConfirmationService,
        private messageService: MessageService,
        private title: Title
    ) { }

    ngOnInit(): void {
        this.title.setTitle('Pesquisa de Motivo da perda');
    }

    pesquisar(pagina = 0) {
        this.filtro.pagina = pagina;
        this.produtosService.pesquisar(this.filtro)
            .then(resultado => {
                this.totalRegistros = resultado.total;
                this.produtos = resultado.produtos;
                this.loading = false;
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    aoMudarPagina(event: LazyLoadEvent) {
        const pagina = event.first / event.rows;
        this.pesquisar(pagina);
    }

    confirmarExclusao(produto: any) {
        this.confirmation.confirm({
            message: 'Tem certeza que deseja excluir?',
            accept: () => {
                this.excluir(produto);
            }
        });
    }

    excluir(produto: any) {
        this.produtosService.excluir(produto.id)
            .then(() => {
                if (this.grid.first === 0) {
                    this.pesquisar();
                } else {
                    this.grid.first = 0;
                    this.pesquisar();
                }
                this.messageService.add({ severity: 'success', detail: 'Motivo da perda excluída com sucesso!' });
            })
            .catch(erro => this.errorHandler.handle(erro));
    }
    alternarVisibilidade(produto: any): void {
        const novoVis = produto.visibilidade;
        this.produtosService.mudarVisibilidade(produto.id, novoVis)
            .then(() => {
                const acao = novoVis ? 'ativado' : 'desativado';
                produto.visibilidade = novoVis;
                this.messageService.add({ severity: 'success', detail: `Produto ${acao} com sucesso!` });
            })
            .catch(erro => this.errorHandler.handle(erro));
    }
    backClicked() {
        this._location.back();
    }
}
