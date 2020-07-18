import { Router, ActivatedRoute } from '@angular/router';
import { Negociacao } from './../../models/negociacao.models';
import { NegociacoesService } from './../../services/negociacoes.service';
import { ErrorHandlerService } from './../../../core/error-handler.service';
import { Title } from '@angular/platform-browser';
import { MenuItem, MessageService, ConfirmationService } from 'primeng/api';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Location } from '@angular/common';
import { Table } from 'primeng/table';

@Component({
    selector: 'app-negociacaoes-detalha',
    templateUrl: './negociacaoes-detalha.component.html',
    styleUrls: ['./negociacaoes-detalha.component.scss']
})
export class NegociacaoesDetalhaComponent {

    estatus = [
        { label: 'Em andamento', value: 'EMDAMENTO' },
        { label: 'Vendido', value: 'VENDIDO' },
        { label: 'Perdida', value: 'PERDIDA' },
        { label: 'Pausado', value: 'PAUSADO' }
    ];
    items: MenuItem[];
    activeItem: MenuItem;
    activeIndex: number = 0;
    negociacoes: any;
    @ViewChild('dt') table: Table;

    constructor(
        private negociacoesService: NegociacoesService,
        private errorHandler: ErrorHandlerService,
        private confirmation: ConfirmationService,
        private messageService: MessageService,
        private route: ActivatedRoute,
        private router: Router,
        private title: Title,
        private _location: Location,
    ) { }


    ngOnInit() {
        this.title.setTitle('Detalhar de Negociação');
        this.carregarItems();
        const idNegociacao = this.route.snapshot.params['id'];

        this.carregarNegociacao(idNegociacao);
        console.log(this.negociacoes);
        this.carregarNegociacao
        this.activeItem = this.items[0];
    }

    carregarNegociacao(id: number) {
        this.negociacoesService.buscarPorCodigo(id)
            .then(negociacao => {
                this.negociacoes = negociacao;
                
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

    confirmarExclusao(negociacao: any) {
        this.confirmation.confirm({
            message: 'Tem certeza que deseja excluir?',
            accept: () => {
                this.excluir(negociacao);
            }
        });
    }

    excluir(negociacao: any) {
        this.negociacoesService.excluir(negociacao.id)
            .then(() => {
                if (this.table.first === 0) {
                } else {
                    this.table.first = 0;
                }
                this.messageService.add({ severity: 'success', detail: 'Negociação excluída com sucesso!' });
            })
            .catch(erro => this.errorHandler.handle(erro));
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

    backClicked() {
        this._location.back();
    }
}
