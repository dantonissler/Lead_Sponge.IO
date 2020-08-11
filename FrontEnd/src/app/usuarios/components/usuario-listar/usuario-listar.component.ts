import { UsuarioService } from '../../services/usuario.service';
import { Title } from '@angular/platform-browser';
import { ConfirmationService, MessageService, LazyLoadEvent } from 'primeng/api';
import { ErrorHandlerService } from '../../../core/error-handler.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { UsuarioFiltro } from '../../services/usuario.service';
import { Location } from '@angular/common';

@Component({
    selector: 'app-usuario-listar',
    templateUrl: './usuario-listar.component.html',
    styleUrls: ['./usuario-listar.component.scss']
})
export class UsuarioListarComponent implements OnInit {

    totalRegistros = 0;
    filtro = new UsuarioFiltro();
    usuarios = [];
    loading = true;
    @ViewChild('tabela', { static: true }) grid;

    constructor(
        private usuarioService: UsuarioService,
        private _location: Location,
        private errorHandler: ErrorHandlerService,
        private confirmation: ConfirmationService,
        private messageService: MessageService,
        private title: Title
    ) { }

    ngOnInit() {
        this.title.setTitle('Pesquisa de usuarios');
    }
    pesquisar(pagina = 0) {
        this.filtro.pagina = pagina;

        this.usuarioService.pesquisar(this.filtro)
            .then(resultado => {
                this.totalRegistros = resultado.total;
                this.usuarios = resultado.usuarios;
                this.loading = false;
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    aoMudarPagina(event: LazyLoadEvent) {
        this.filtro.itensPorPagina = event.rows;
        const pagina = event.first / event.rows;
        this.pesquisar(pagina);
    }

    confirmarExclusao(usuario: any) {
        this.confirmation.confirm({
            message: 'Tem certeza que deseja excluir?',
            accept: () => {
                this.excluir(usuario);
            }
        });
    }

    excluir(usuario: any) {
        this.usuarioService.excluir(usuario.id)
            .then(() => {
                if (this.grid.first === 0) {
                    this.pesquisar();
                } else {
                    this.grid.first = 0;
                }

                this.messageService.add({ severity: 'success', detail: 'Usuario excluída com sucesso!' });
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    alternarStatus(usuario: any): void {
        const novoStatus = usuario.enabled;
        this.usuarioService.mudarStatus(usuario.id, novoStatus)
            .then(() => {
                const acao = novoStatus ? 'ativada' : 'desativada';
                usuario.enabled = novoStatus;
                this.messageService.add({ severity: 'success', detail: `Usuario ${acao} com sucesso!` });
            })
            .catch(erro => this.errorHandler.handle(erro));
    }
    backClicked() {
        this._location.back();
    }
}
