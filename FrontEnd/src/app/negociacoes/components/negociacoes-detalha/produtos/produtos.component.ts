import { Router, ActivatedRoute } from '@angular/router';
import { NegociacoesService } from './../../../services/negociacoes.service';
import { ErrorHandlerService } from './../../../../core/error-handler.service';
import { MessageService } from 'primeng/api';
import { ProdutosService } from './../../../../produtos/services/produtos.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Produto } from 'src/app/produtos/models/produto.models';
import { NegociacaoProduto } from './../../../models/negociacao-produto-models';

@Component({
    selector: 'app-produtos',
    templateUrl: './produtos.component.html',
    styleUrls: ['./produtos.component.scss']
})
export class ProdutosComponent implements OnInit {

    produtos = new Produto();
    negociacaoProduto: NegociacaoProduto[];
    produtoSelecionado = new Produto();
    formulario: FormGroup;
    qtd = 1;
    idNeg: number = +this.route.snapshot.params.id;
    optReincidencia = [{ label: 'Único', value: 'UNICO' }, { label: 'Recorrente', value: 'RECORRENTE' }];
    optTipoDesconto = [{ label: '%', value: 'PORCENTAGEM' }, { label: 'R$', value: 'VALOR' }];
    checked = false;

    constructor(
        private negociacoesService: NegociacoesService,
        private produtosService: ProdutosService,
        private formBuilder: FormBuilder,
        private messageService: MessageService,
        private errorHandler: ErrorHandlerService,
        private route: ActivatedRoute,
        private router: Router,
    ) { }

    ngOnInit(): void {
        this.configurarFormulario();
        this.carregarProduto();
    }

    carregarProduto() {
        this.produtosService.listarTodas()
            .then(produto => {
                this.produtos = produto.map(p => ({ nome: p.nome, id: p.id, valor: p.valor }));
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    salvar() {
        this.negociacoesService.adicionarVenda(this.formulario.value)
            .then(negociacaoAdicionado => {
                this.messageService.add({ severity: 'success', detail: 'Negociacao adicionado com sucesso!' });
                this.router.navigate(['/negociacoes/detalhar/' + this.route.snapshot.params.id]);
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

/*     limpar() {
        this.formulario.reset();
        setTimeout(function() {
            this.produtoSelecionado = new Produto();
            this.qtd = 1;
        }.bind(this), 1);
        this.router.navigate(['/negociacoes/detalhar/' + this.route.snapshot.params.id]);
    } */

    configurarFormulario() {
        this.formulario = this.formBuilder.group({
            id: [],
            quantidade: [null, Validators.required],
            reincidencia: ['UNICO', Validators.required],
            temDesconto: [],
            tipoDesconto: ['PORCENTAGEM'],
            desconto: [],
            valor: [null, Validators.required],
            produto: [null, Validators.required],
            negociacao: this.formBuilder.group({
                id: [this.idNeg],
            }),
        });
    }
}
