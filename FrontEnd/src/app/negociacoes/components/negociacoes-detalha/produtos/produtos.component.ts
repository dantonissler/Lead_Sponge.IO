import { Router, ActivatedRoute } from '@angular/router';
import { NegociacaoProdutoService } from './../../../services/negociacao-produto.service';
import { ErrorHandlerService } from './../../../../core/error-handler.service';
import { MessageService, ConfirmationService } from 'primeng/api';
import { ProdutosService } from './../../../../produtos/services/produtos.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Produto } from 'src/app/produtos/models/produto.models';
import { NegociacoesService } from 'src/app/negociacoes/services/negociacoes.service';
import { Table } from 'primeng/table';

@Component({
    selector: 'app-produtos',
    templateUrl: './produtos.component.html',
    styleUrls: ['./produtos.component.scss']
})
export class ProdutosComponent implements OnInit {

    qtd = 1;
    checked = false;
    produtos = new Produto();
    produtoSelecionado = new Produto();
    formulario: FormGroup;
    negociacaoProdutos: any;
    @ViewChild('dt') table: Table;
    loading = true;
    idNeg: number = +this.route.snapshot.params.id;
    optReincidencia = [{ label: 'Único', value: 'UNICO' }, { label: 'Recorrente', value: 'RECORRENTE' }];
    optTipoDesconto = [{ label: '%', value: 'PORCENTAGEM' }, { label: 'R$', value: 'VALOR' }];

    constructor(
        private negociacaoProdutoService: NegociacaoProdutoService,
        private negociacoesService: NegociacoesService,
        private produtosService: ProdutosService,
        private formBuilder: FormBuilder,
        private messageService: MessageService,
        private errorHandler: ErrorHandlerService,
        private confirmation: ConfirmationService,
        private route: ActivatedRoute,
    ) { }

    ngOnInit(): void {
        this.carregarNegociacao(this.idNeg);
        this.configurarFormulario();
        this.carregarProduto();
    }

    carregarNegociacao(id: number) {
        this.negociacoesService.buscarPorCodigo(id)
            .then(negociacao => {
                this.negociacaoProdutos = negociacao.negociacaoProdutos;
                this.loading = false;
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    carregarProduto() {
        this.produtosService.listarTodas()
            .then(produto => {
                this.produtos = produto.map(p => ({ nome: p.nome, id: p.id, valor: p.valor }));
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

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

    salvar() {
        this.negociacaoProdutoService.adicionarVenda(this.formulario.value)
            .then(negociacaoAdicionado => {
                this.carregarNegociacao(this.idNeg);
                this.configurarFormulario();
                this.carregarProduto();
                this.produtoSelecionado = new Produto();
                this.qtd = 1;
                this.messageService.add({ severity: 'success', detail: 'Negociacao adicionado com sucesso!' });
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


    confirmarExclusao(negociacaoProduto: any) {
        this.confirmation.confirm({
            message: 'Tem certeza que deseja excluir?',
            accept: () => {
                this.excluir(negociacaoProduto);
            }
        });
    }

    excluir(negociacaoProduto: any) {
        this.negociacaoProdutoService.excluir(negociacaoProduto.id)
            .then(() => {
                this.carregarNegociacao(this.idNeg);
                this.messageService.add({ severity: 'success', detail: 'Negociação excluída com sucesso!' });
            })
            .catch(erro => this.errorHandler.handle(erro));
    }
}
