import { Title } from '@angular/platform-browser';
import { ErrorHandlerService } from './../../../core/error-handler.service';
import { ConfirmationService, MessageService, LazyLoadEvent } from 'primeng/api';
import { SegmentosService } from './../../services/segmentos.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { SegmentoFiltro } from '../../services/segmentos.service';
import { Location } from '@angular/common';

@Component({
    selector: 'app-segmentos-pesquisa',
    templateUrl: './segmentos-pesquisa.component.html',
    styleUrls: ['./segmentos-pesquisa.component.scss']
})
export class SegmentosPesquisaComponent implements OnInit {

    totalRegistros = 0;
    filtro = new SegmentoFiltro();
    segmentos = [];
    loading: boolean = true;
    @ViewChild('tabela', { static: true }) grid;

    constructor(
        private segmentosService: SegmentosService,
        private _location: Location,
        private errorHandler: ErrorHandlerService,
        private confirmation: ConfirmationService,
        private messageService: MessageService,
        private title: Title
    ) { }

    ngOnInit(): void {
        this.title.setTitle('Pesquisa de Segmentos');
    }

    pesquisar(pagina = 0) {
        this.filtro.pagina = pagina;
        this.segmentosService.pesquisar(this.filtro)
            .then(resultado => {
                this.totalRegistros = resultado.total;
                this.segmentos = resultado.segmentos;
                this.loading = false;
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    aoMudarPagina(event: LazyLoadEvent) {
        this.filtro.itensPorPagina = event.rows;
        const pagina = event.first / event.rows;
        this.pesquisar(pagina);
    }

    confirmarExclusao(campanha: any) {
        this.confirmation.confirm({
            message: 'Tem certeza que deseja excluir?',
            accept: () => {
                this.excluir(campanha);
            }
        });
    }

    excluir(campanha: any) {
        this.segmentosService.excluir(campanha.id)
            .then(() => {
                if (this.grid.first === 0) {
                    this.pesquisar();
                } else {
                    this.grid.first = 0;
                    this.pesquisar();
                }
                this.messageService.add({ severity: 'success', detail: 'Campanha excluída com sucesso!' });
            })
            .catch(erro => this.errorHandler.handle(erro));
    }
    backClicked() {
        this._location.back();
    }
}
