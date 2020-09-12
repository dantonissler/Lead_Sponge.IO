import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { UsuarioService } from '../../services/usuario.service';
import { RoleService } from '../../services/role.service';
import { MessageService } from 'primeng/api';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { ActivatedRoute } from '@angular/router';
import { Usuario } from '../../models/usuario.model';

@Component({
    selector: 'app-usuario-detalhar',
    templateUrl: './usuario-detalhar.component.html',
    styleUrls: ['./usuario-detalhar.component.scss']
})
export class UsuarioDetalharComponent implements OnInit {

    foto: string;
    urlFoto: string;
    formularioImg: FormGroup;
    uploadEmAndamento = false;
    idNeg: number = +this.route.snapshot.params.id;
    get urlUploadAnexo() { return this.usuarioService.urlUploadAnexo(); }

    constructor(
        private usuarioService: UsuarioService,
        private messageService: MessageService,
        private errorHandler: ErrorHandlerService,
        private route: ActivatedRoute
    ) { }

    ngOnInit(): void {
        this.carregarUsuario(this.idNeg);
    }

    carregarUsuario(id: number) {
        this.usuarioService.buscarPorCodigo(id)
            .then(usuario => {
                usuario.password = '';
                usuario.confirmarPassword = '';
                this.foto = usuario.foto;
                this.urlFoto = usuario.urlFoto;
                /* this.formularioImg.patchValue(usuario); */
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

    antesUploadAnexo() { this.uploadEmAndamento = true; }

    onFileUpload(data) {
        const file = data.originalEvent.body;
        this.usuarioService.atualizarImg(this.idNeg, file.nome)
        .then(() => {
            this.carregarUsuario(this.idNeg);
            this.messageService.add({ severity: 'success', detail: `Imagem alterada com sucesso!` });
        })
        .catch(erro => this.errorHandler.handle(erro));
        this.uploadEmAndamento = false;
    }

    erroUpload(event) {
        this.messageService.add({ severity: 'error', detail: 'Erro ao tentar enviar imagem!' });
        this.uploadEmAndamento = false;
    }

    removerAnexo(): void {
        this.usuarioService.removerImg(this.idNeg)
            .then(() => {
                this.carregarUsuario(this.idNeg);
                this.messageService.add({ severity: 'success', detail: `Imagem removida com sucesso!` });
            })
            .catch(erro => this.errorHandler.handle(erro));
    }
}
