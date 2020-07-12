import { NegociacoesService } from './../../services/negociacoes.service';
import { Negociacao } from './../../models/negociacao.models';
import { ErrorHandlerService } from './../../../core/error-handler.service';
import { EstagioNegociacaoService } from './../../../estagioNegociacao/services/estagio-negociacao.service';
import { MenuItem } from 'primeng/api/menuitem';
import { MessageService } from 'primeng/api';
import { Component, OnInit, ViewEncapsulation, Input } from '@angular/core';

@Component({
    selector: 'app-funil-de-vendas',
    templateUrl: './funil-de-vendas.component.html',
    providers: [MessageService],
    styleUrls: ['./funil-de-vendas.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class FunilDeVendasComponent implements OnInit {

    /*
    * TODO : Estudar uma forma de colocar o metodo "alterarFunil" dentro da 
    * class NegociacoesPesquisaComponent,por que não está aparecendo a mensagem 
    * e a alteração esta sendo feita pelo id e não pela possição conforme o planejado.
    */

    @Input() negociacao: Negociacao;
    items: MenuItem[];
    activeIndex: number = 0;

    constructor(
        private negociacoesService: NegociacoesService,
        private estagioNegociacaoService: EstagioNegociacaoService,
        private messageService: MessageService,
        private errorHandler: ErrorHandlerService,
    ) { }

    ngOnInit() {
        this.activeIndex = this.negociacao.estagio.id - 1;
        this.carregarFunilDeVendas();
    }


    carregarFunilDeVendas() {
        this.estagioNegociacaoService.listarTodas()
            .then(estagios => {
                this.items = estagios.map(c => ({ label: c.nome, activeIndex: c.posicao, titulo: c.apelido }));
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    alterarFunil(negociacao: any): void {
        const novoVis = negociacao.estagio;
        novoVis.id = this.activeIndex+1;
        this.negociacoesService.mudarEstagio(negociacao.id, novoVis)
            .then(() => {
                const acao = novoVis.nome;
                negociacao.estagio = novoVis;
                this.messageService.add({ severity: 'success', detail: `estagio alterado para ${acao} com sucesso!` });
            })
            .catch(erro => this.errorHandler.handle(erro));
    }
}