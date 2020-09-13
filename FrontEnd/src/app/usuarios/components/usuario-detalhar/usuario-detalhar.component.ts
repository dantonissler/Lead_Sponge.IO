import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { UsuarioService } from '../../services/usuario.service';
import { MessageService } from 'primeng/api';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { ActivatedRoute } from '@angular/router';
import { Usuario } from '../../models/usuario.model';
import { RoleService } from '../../services/role.service';

@Component({
    selector: 'app-usuario-detalhar',
    templateUrl: './usuario-detalhar.component.html',
    styleUrls: ['./usuario-detalhar.component.scss']
})
export class UsuarioDetalharComponent implements OnInit {

    checked = true;
    usuario = new Usuario();
    formulario: FormGroup;
    roles = [];
    uploadEmAndamento = false;
    idNeg: number = +this.route.snapshot.params.id;
    get urlUploadAnexo() { return this.usuarioService.urlUploadAnexo(); }

    constructor(
        private usuarioService: UsuarioService,
        private roleService: RoleService,
        private messageService: MessageService,
        private formBuilder: FormBuilder,
        private errorHandler: ErrorHandlerService,
        private route: ActivatedRoute
    ) { }

    ngOnInit(): void {
        this.configurarFormulario();
        this.carregarUsuario(this.idNeg);
        this.carregarRole();
    }

    carregarUsuario(id: number) {
        this.usuarioService.buscarPorCodigo(id)
            .then(usuario => {
                usuario.password = '';
                usuario.confirmarPassword = '';
                this.usuario = usuario;
                this.formulario.patchValue(usuario);
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    carregarRole() {
        this.roleService.listarTodas()
            .then(roles => {
                this.roles = roles;
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    configurarFormulario() {
        this.formulario = this.formBuilder.group({
            username: [null, [this.validarObrigatoriedade, this.validarTamanhoMinimo(4)]],
            nomeCompleto: [null, [this.validarObrigatoriedade, this.validarTamanhoMinimo(4)]],
            email: [null, [this.validarObrigatoriedade, Validators.email]],
            roles: []
        });
    }

    validarObrigatoriedade(input: FormControl) {
        return (input.value ? null : { obrigatoriedade: true });
    }

    validarTamanhoMinimo(valor: number) {
        return (input: FormControl) => {
            return (!input.value || input.value.length >= valor) ? null : { tamanhoMinimo: { tamanho: valor } };
        };
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

    atualizarUsuarioDTO() {
        this.usuarioService.atualizarUsuarioDTO(this.idNeg, this.formulario.value)
            .then(usuario => {
                this.formulario.patchValue(usuario);
                this.messageService.add({ severity: 'success', detail: 'Usuario alterado com sucesso!' });
            })
            .catch(erro => this.errorHandler.handle(erro));
    }
}
