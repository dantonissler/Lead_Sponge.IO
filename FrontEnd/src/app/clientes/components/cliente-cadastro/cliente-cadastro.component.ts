import { Usuario } from './../../../usuarios/models/usuario.model';
import { AuthService } from './../../../usuarios/services/auth.service';
import { UsuarioService, UsuarioFiltro } from './../../../usuarios/services/usuario.service';
import { Title } from '@angular/platform-browser';
import { ErrorHandlerService } from './../../../core/error-handler.service';
import { MessageService, SelectItem } from 'primeng/api';
import { ClienteService } from './../../services/cliente.service';
import { Cliente } from './../../models/cliente.models';
import { Component, OnInit, LOCALE_ID } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators, FormArray } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SegmentosService } from 'src/app/segmentos/services/segmentos.service';

@Component({
    selector: 'app-cliente-cadastro',
    templateUrl: './cliente-cadastro.component.html',
    styleUrls: ['./cliente-cadastro.component.scss']
})
export class ClienteCadastroComponent implements OnInit {
    id: number;
    formulario: FormGroup;
    segmentos: SelectItem[];
    selectedSegmentos: string[] = [];
    tipos: SelectItem[];
    selectedTipo = 'COMERCIAL';
    filtro = new UsuarioFiltro();
    get editando() { return Boolean(this.formulario.get('id').value); }

    constructor(
        private usuarioService: UsuarioService,
        private segmentoService: SegmentosService,
        private clienteService: ClienteService,
        private messageService: MessageService,
        private errorHandler: ErrorHandlerService,
        private route: ActivatedRoute,
        private router: Router,
        private title: Title,
        public auth: AuthService,
        private formBuilder: FormBuilder
    ) { }

    ngOnInit(): void {
        this.carregarUsuario(this.auth.jwtPayload?.user_name);
        this.configurarFormulario();
        const idCliente = this.route.snapshot.params.id;
        this.title.setTitle('Novo Cliente');
        this.carregarSegmento();
        this.carregarTipo();
        if (idCliente) {
            this.carregarCliente(idCliente);
        }
    }

    carregarUsuario(username: string) {
        this.usuarioService.buscarPeloNome(username)
            .then(usuario => {
                this.id = usuario.id;
            })
            .catch(erro => this.errorHandler.handle(erro));
    }


    carregarCliente(id) {
        this.clienteService.buscarPorCodigo(id)
            .then(cliente => {
                this.formulario.patchValue(cliente);
                this.atualizarTituloEdicao();
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    carregarSegmento() {
        return this.segmentoService.listarTodas()
            .then(segmentos => {
                this.segmentos = segmentos.map(segmento => ({ label: segmento.nome, value: { nome: segmento.nome, id: segmento.id } }));
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    carregarTipo() {
        this.tipos = [
            { label: 'Comercial', value: 'COMERCIAL', icon: 'pi pi-briefcase' },
            { label: 'Residencial', value: 'RESIDENCIAL', icon: 'pi pi-home' },
            { label: 'Pessoal', value: 'PESSOAL', icon: 'pi pi-mobile' },
            { label: 'Fax', value: 'FAX', icon: 'pi pi-print' }
        ];
    }
    configurarFormulario() {
        this.formulario = this.formBuilder.group({
            id: [],
            nome: [null, [this.validarObrigatoriedade, this.validarTamanhoMinimo(4)]],
            segmentos: [this.selectedSegmentos],
            responsavel: this.formBuilder.group({
                id: []
            }),
            url: [],
            resumo: [],
            contato: this.formBuilder.array([this.criarContato()])
        });
    }

    criarContato(): FormGroup {
        return this.formBuilder.group({
            id: [],
            nome: [null, [this.validarObrigatoriedade, this.validarTamanhoMinimo(4)]],
            cargo: [],
            telefone: this.formBuilder.array([this.criarTelefone()]),
            email: this.formBuilder.array([this.criarEmail()]),
        });
    }

    criarTelefone(): FormGroup {
        return this.formBuilder.group({
            id: [],
            numero: [null, [this.validarObrigatoriedade]],
            tipo: [this.selectedTipo, Validators.required]
        });
    }

    criarEmail(): FormGroup {
        return this.formBuilder.group({
            id: [],
            email: [null, [this.validarObrigatoriedade, Validators.email]],
        });
    }

    getContatos(form): FormArray {
        return form.get('contato').controls;
    }

    getTelefones(form): FormArray {
        return form.get('telefone').controls;
    }

    getEmails(form): FormArray {
        return form.get('email').controls;
    }

    adicionarContato() {
        const control = this.formulario.get('contato') as FormArray;
        control.push(this.criarContato());
    }

    adicionarTelefone(j) {
        // tslint:disable-next-line: no-string-literal
        const control = this.formulario.get('contato')['controls'][j].get('telefone') as FormArray;
        control.push(this.criarTelefone());
    }

    adicionarEmail(j) {
        // tslint:disable-next-line: no-string-literal
        const control = this.formulario.get('contato')['controls'][j].get('email') as FormArray;
        control.push(this.criarEmail());
    }

    // remover form group de contatos
    removerContato(i) {
        const control = this.formulario.get('contato') as FormArray;
        control.removeAt(i);

    }

    // remover form group de Telefones
    removerTelefone(j) {
        // tslint:disable-next-line: no-string-literal
        const control = this.formulario.get('contato')['controls'][j].get('telefone') as FormArray;
        control.removeAt(j);
    }

    // remover form group de emails
    removerEmail(j) {
        // tslint:disable-next-line: no-string-literal
        const control = this.formulario.get('contato')['controls'][j].get('email') as FormArray;
        control.removeAt(j);
    }

    validarObrigatoriedade(input: FormControl) {
        return (input.value ? null : { obrigatoriedade: true });
    }

    validarTamanhoMinimo(valor: number) {
        return (input: FormControl) => {
            return (!input.value || input.value.length >= valor) ? null : { tamanhoMinimo: { tamanho: valor } };
        };
    }

    salvar() {
        if (this.editando) {
            this.atualizarCliente();
        } else {
            this.adicionarCliente();
        }
    }

    adicionarCliente() {
        this.clienteService.adicionar(this.formulario.value)
            .then(clienteAdicionado => {
                this.messageService.add({ severity: 'success', detail: 'Cliente adicionado com sucesso!' });
                this.router.navigate(['/clientes', clienteAdicionado.id]);
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    atualizarCliente() {
        this.clienteService.atualizar(this.formulario.value)
            .then(cliente => {
                this.formulario.patchValue(cliente);
                this.messageService.add({ severity: 'success', detail: 'Cliente alterado com sucesso!' });
                this.atualizarTituloEdicao();
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    atualizarTituloEdicao() {
        this.title.setTitle(`Edição de Cliente: ${this.formulario.get('nome').value}`);
    }

    limpar() {
        this.formulario.reset();
        setTimeout(function() {
            this.lancamento = new Cliente();
        }.bind(this), 1);
        this.router.navigate(['/clientes/novo']);
    }
}
