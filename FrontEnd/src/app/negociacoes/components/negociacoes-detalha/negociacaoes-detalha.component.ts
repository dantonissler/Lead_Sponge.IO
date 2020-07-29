import { EstagioNegociacaoService } from './../../../estagioNegociacao/services/estagio-negociacao.service';
import { Negociacao } from './../../models/negociacao.models';
import { ActivatedRoute } from '@angular/router';
import { NegociacoesService } from './../../services/negociacoes.service';
import { ErrorHandlerService } from './../../../core/error-handler.service';
import { Title } from '@angular/platform-browser';
import { MenuItem, MessageService, LazyLoadEvent } from 'primeng/api';
import { Component, ViewEncapsulation } from '@angular/core';
import { Location } from '@angular/common';

@Component({
    selector: 'app-negociacaoes-detalha',
    templateUrl: './negociacaoes-detalha.component.html',
    styleUrls: ['./negociacaoes-detalha.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class NegociacaoesDetalhaComponent {

    estatus = [
        { label: 'Em andamento', value: 'EMDAMENTO' },
        { label: 'Vendido', value: 'VENDIDO' },
        { label: 'Perdida', value: 'PERDIDA' },
        { label: 'Pausado', value: 'PAUSADO' }
    ];

    items: MenuItem[];
    funil: MenuItem[];
    activeItem: MenuItem;
    activeIndex: number = 0;
    activeIndexFunil: number = 0;
    negociacao = new Negociacao();
    editing = false
    ptBR: any;

    constructor(
        private negociacoesService: NegociacoesService,
        private estagioNegociacaoService: EstagioNegociacaoService,
        private errorHandler: ErrorHandlerService,
        private messageService: MessageService,
        private route: ActivatedRoute,
        private title: Title,
        private _location: Location,
    ) { }


    ngOnInit() {
        this.title.setTitle('Detalhar de Negociação');
        const idNegociacao = this.route.snapshot.params['id'];
        this.carregarNegociacao(idNegociacao);
        this.traducaoCalendario();
        this.carregarItems();
        this.carregarFunilDeVendas();
        this.activeItem = this.items[0];
    }

    carregarNegociacao(id: number) {
        this.negociacoesService.buscarPorCodigo(id)
            .then(negociacao => {
                this.negociacao = negociacao;
                this.activeIndexFunil = negociacao.estagio.id - 1;
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    carregarFunilDeVendas() {
        this.estagioNegociacaoService.listarTodas()
            .then(estagios => {
                this.funil = estagios.map(c => ({ label: c.nome, activeIndexFunil: c.posicao, titulo: c.apelido }));
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    carregarItems() {
        this.items = [
            {
                label: 'HISTÓRICO',
                command: (onclick) => { this.activeIndex = 0 }
            },
            {
                label: 'TAREFAS',
                command: (onclick) => { this.activeIndex = 1 }
            },
            {
                label: 'CONTATOS',
                command: (onclick) => { this.activeIndex = 2 }
            },
            {
                label: 'PRODUTOS E SERVIÇOS',
                command: (onclick) => { this.activeIndex = 3 }
            }
        ];
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

    alterarFunil(negociacao: any): void {
        const novoVis = negociacao.estagio;
        novoVis.id = this.activeIndexFunil + 1;
        this.negociacoesService.mudarEstagio(negociacao.id, novoVis)
            .then(() => {
                const acao = novoVis.nome;
                negociacao.estagio = novoVis;
                this.messageService.add({ severity: 'success', detail: `estagio alterado para ${acao} com sucesso!` });
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    alternarDPE(event: LazyLoadEvent, negociacoes: any): void {
        const novodata = event;
        console.log(event);
        this.negociacoesService.mudarData(negociacoes.id, novodata)
            .then(() => {
                const acao = novodata;
                negociacoes.dataPrevistaEncerramento = novodata;
                this.messageService.add({ severity: 'success', detail: `Avaliação da negociação igual à ${acao} alterado com sucesso!` });
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    backClicked() {
        this._location.back();
    }

    traducaoCalendario(){
        this.ptBR = {
            dayNames: [ "domingo","segunda-feira","terça-feira","quarta-feira","quinta-feira","sexta-feira","sábado" ],
            dayNamesShort: [ "dom","seg","ter","qua","qui","sex","sáb" ],
            dayNamesMin: [ "D","S","T","Q","Q","S","S" ],
            monthNames: [ "janeiro","fevereiro","março","abril","maio","junho","julho","agosto","setembro","outubro","novembro","dezembro" ],
            monthNamesShort: [ "jan","fev","mar","abr","mai","jun","jul","ago","sep","out","nov","dez" ],
            today: 'Hoje',
            clear: 'Limpar'
        }
    }
}
