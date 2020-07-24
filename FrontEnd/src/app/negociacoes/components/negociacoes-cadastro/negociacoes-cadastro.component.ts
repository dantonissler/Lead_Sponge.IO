import { FontesService } from './../../../fonteNegociacao/services/fontes.service';
import { CampanhasService } from './../../../campanhas/services/campanhas.service';
import { Title } from '@angular/platform-browser';
import { ErrorHandlerService } from './../../../core/error-handler.service';
import { MessageService } from 'primeng/api';
import { ClienteService } from './../../../clientes/services/cliente.service';
import { NegociacoesService } from './../../services/negociacoes.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Negociacao } from '../../models/negociacao.models';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
    selector: 'app-negociacoes-cadastro',
    templateUrl: './negociacoes-cadastro.component.html',
    styleUrls: ['./negociacoes-cadastro.component.scss']
})
export class NegociacoesCadastroComponent implements OnInit {

    formulario: FormGroup;
    negociacoes = new Negociacao();
    campanha = [];
    cliente = [];
    fonte = [];

    constructor(
        private negociacoesService: NegociacoesService,
        private clienteService: ClienteService,
        private fontesService: FontesService,
        private campanhasService: CampanhasService,
        private messageService: MessageService,
        private errorHandler: ErrorHandlerService,
        private route: ActivatedRoute,
        private router: Router,
        private title: Title,
        private formBuilder: FormBuilder
    ) { }

    ngOnInit(): void {
        this.configurarFormulario();
        const idNegociacao = this.route.snapshot.params['id'];
        this.title.setTitle('Nova Negociação');
        if (idNegociacao) {
            this.carregarNegociacao(idNegociacao);
        }
        this.carregarCliente();
        this.carregarCampanha();
        this.carregarFonte();
    }
    get editando() {
        return Boolean(this.formulario.get('id').value);
    }

    carregarNegociacao(id: number) {
        this.negociacoesService.buscarPorCodigo(id)
            .then(negociacao => {
                this.formulario.patchValue(negociacao);
                this.atualizarTituloEdicao();
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    carregarCliente() {
        this.clienteService.listarTodas()
            .then(cliente => {
                this.cliente = cliente
                    .map(p => ({ label: p.nome, value: p.id }));
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    carregarCampanha() {
        this.campanhasService.listarTodas()
            .then(campanha => {
                this.campanha = campanha
                    .map(p => ({ label: p.nome, value: p.id }));
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    carregarFonte() {
        this.fontesService.listarTodas()
            .then(fonte => {
                this.fonte = fonte
                    .map(p => ({ label: p.nome, value: p.id }));
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    salvar() {
        if (this.editando) {
            this.atualizarNegociacao();
        } else {
            this.adicionarNegociacao();
        }
    }

    adicionarNegociacao() {
        this.negociacoesService.adicionar(this.formulario.value)
            .then(negociacaoAdicionado => {
                this.messageService.add({ severity: 'success', detail: 'Negociacao adicionado com sucesso!' });
                this.router.navigate(['/negociacoes', negociacaoAdicionado.id]);
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    atualizarNegociacao() {
        this.negociacoesService.atualizar(this.formulario.value)
            .then(negociacao => {
                this.formulario.patchValue(negociacao);

                this.messageService.add({ severity: 'success', detail: 'Negociacao alterado com sucesso!' });
                this.atualizarTituloEdicao();
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    atualizarTituloEdicao() {
        this.title.setTitle(`Edição da negociacao: ${this.negociacoes.nome}`);
    }

    configurarFormulario() {
        this.formulario = this.formBuilder.group({
            id: [],
            estatus: ['EMDAMENTO', Validators.required],
            nome: [null, [this.validarObrigatoriedade, this.validarTamanhoMinimo(4)]],
            avaliacao: [1, Validators.required],
            cliente: this.formBuilder.group({
                id: [null, Validators.required],
                nome: []
            }),
            fonte: this.formBuilder.group({
                id: [null, Validators.required],
                nome: []
            }),
            campanha: this.formBuilder.group({
                id: [null, Validators.required],
                nome: []
            }),
            estagio: this.formBuilder.group({
                id: [1, Validators.required],
            }),
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

    limpar() {
        this.formulario.reset();
        setTimeout(function () {
            this.negociacao = new Negociacao();
        }.bind(this), 1);
        this.router.navigate(['/negociacoes/novo']);
    }
}
