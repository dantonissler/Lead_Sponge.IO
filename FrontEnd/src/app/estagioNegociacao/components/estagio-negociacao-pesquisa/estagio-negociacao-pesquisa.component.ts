import { EstagioFiltro } from './../../services/estagio-negociacao.service';
import { Title } from '@angular/platform-browser';
import { ConfirmationService, MessageService, LazyLoadEvent } from 'primeng/api';
import { ErrorHandlerService } from './../../../core/error-handler.service';

import { Component, OnInit, ViewChild } from '@angular/core';
import { EstagioNegociacaoService } from '../../services/estagio-negociacao.service';
import { Location } from '@angular/common';

@Component({
    selector: 'app-estagio-negociacao-pesquisa',
    templateUrl: './estagio-negociacao-pesquisa.component.html',
    styleUrls: ['./estagio-negociacao-pesquisa.component.scss']
})
export class EstagioNegociacaoPesquisaComponent implements OnInit {

    totalRegistros = 0;
    filtro = new EstagioFiltro();
    estagios = [];
    loading: boolean = true;
    @ViewChild('tabela', { static: true }) grid;

    constructor(
        private estagioNegociacaoService: EstagioNegociacaoService,
        private _location: Location,
        private errorHandler: ErrorHandlerService,
        private confirmation: ConfirmationService,
        private messageService: MessageService,
        private title: Title
    ) { }

    ngOnInit(): void {
        this.title.setTitle('Pesquisa de Estagio');
    }

    pesquisar(pagina = 0) {
        this.filtro.pagina = pagina;
        this.estagioNegociacaoService.pesquisar(this.filtro)
            .then(resultado => {
                this.totalRegistros = resultado.total;
                this.estagios = resultado.estagios;
                this.loading = false;
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    aoMudarPagina(event: LazyLoadEvent) {
        this.filtro.itensPorPagina = event.rows;
        const pagina = event.first / event.rows;
        this.pesquisar(pagina);
    }

    confirmarExclusao(estagio: any) {
        this.confirmation.confirm({
            message: 'Tem certeza que deseja excluir?',
            accept: () => {
                this.excluir(estagio);
            }
        });
    }

    excluir(estagio: any) {
        this.estagioNegociacaoService.excluir(estagio.id)
            .then(() => {
                if (this.grid.first === 0) {
                    this.pesquisar();
                } else {
                    this.grid.first = 0;
                    this.pesquisar();
                }
                this.messageService.add({ severity: 'success', detail: 'Estagio excluída com sucesso!' });
            })
            .catch(erro => this.errorHandler.handle(erro));
    }
    backClicked() {
        this._location.back();
    }
}
