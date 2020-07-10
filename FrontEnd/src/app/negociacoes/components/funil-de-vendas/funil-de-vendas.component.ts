import { Estagio } from './../../../estagioNegociacao/models/estagio-negociacao.models';
import { Negociacao } from './../../models/negociacao.models';
import { ErrorHandlerService } from './../../../core/error-handler.service';
import { EstagioNegociacaoService } from './../../../estagioNegociacao/services/estagio-negociacao.service';
import { MenuItem } from 'primeng/api/menuitem';
import { MessageService } from 'primeng/api';
import { Component, OnInit, ViewEncapsulation, Input, EventEmitter, Output } from '@angular/core';

@Component({
    selector: 'app-funil-de-vendas',
    templateUrl: './funil-de-vendas.component.html',
    providers: [MessageService],
    styleUrls: ['./funil-de-vendas.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class FunilDeVendasComponent implements OnInit {

    @Input() estagio: Estagio;
    @Output() respostaEstagio: EventEmitter<any> = new EventEmitter();
    items: MenuItem[];
    activeIndex: number = 0;

    constructor(
        private estagioNegociacaoService: EstagioNegociacaoService,
        private errorHandler: ErrorHandlerService,
    ) { }

    ngOnInit() {
        this.activeIndex = this.estagio.posicao - 1;
        this.carregarFunilDeVendas();
    }

    carregarFunilDeVendas() {
        this.estagioNegociacaoService.listarTodas()
            .then(estagios => {
                this.items = estagios.map(c => ({ label: c.nome, activeIndex: c.posicao, titulo: c.apelido }));
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    alterarFunil(estagio: any){
        console.log(this.respostaEstagio.emit(estagio));
    }
}