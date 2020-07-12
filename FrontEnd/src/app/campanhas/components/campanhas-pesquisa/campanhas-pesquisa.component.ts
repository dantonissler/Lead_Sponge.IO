import { Title } from '@angular/platform-browser';
import { ConfirmationService, MessageService, LazyLoadEvent } from 'primeng/api';
import { ErrorHandlerService } from './../../../core/error-handler.service';
import { CampanhaFiltro, CampanhasService } from './../../services/campanhas.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Location } from '@angular/common';

@Component({
    selector: 'app-campanhas-pesquisa',
    templateUrl: './campanhas-pesquisa.component.html',
    styleUrls: ['./campanhas-pesquisa.component.scss']
})
export class CampanhasPesquisaComponent implements OnInit {

    totalRegistros = 0;
    filtro = new CampanhaFiltro();
    campanhas = [];
    loading: boolean = true;
    @ViewChild('tabela', { static: true }) grid;

    constructor(
        private campanhasService: CampanhasService,
        private _location: Location,
        private errorHandler: ErrorHandlerService,
        private confirmation: ConfirmationService,
        private messageService: MessageService,
        private title: Title
    ) { }

    ngOnInit(): void {
        this.title.setTitle('Pesquisa de Campanhas');
    }

    pesquisar(pagina = 0) {
        this.filtro.pagina = pagina;
        this.campanhasService.pesquisar(this.filtro)
            .then(resultado => {
                this.totalRegistros = resultado.total;
                this.campanhas = resultado.campanhas;
                this.loading = false;
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    aoMudarPagina(event: LazyLoadEvent) {
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
        this.campanhasService.excluir(campanha.id)
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
