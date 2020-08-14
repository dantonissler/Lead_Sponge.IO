import { Router, ActivatedRoute } from '@angular/router';
import { NegociacaoProdutoService } from './../../../services/negociacao-produto.service';
import { ErrorHandlerService } from './../../../../core/error-handler.service';
import { MessageService, ConfirmationService } from 'primeng/api';
import { ProdutosService } from './../../../../produtos/services/produtos.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { NegociacoesService } from 'src/app/negociacoes/services/negociacoes.service';
import { Table } from 'primeng/table';
import { NegociacaoProduto } from 'src/app/negociacoes/models/negociacao-produto-models';

@Component({
    selector: 'app-produtos',
    templateUrl: './produtos.component.html',
    styleUrls: ['./produtos.component.scss']
})
export class ProdutosComponent implements OnInit {

    qtd = 1;
    desconto = null;
    checked = false;
    checkedEdit = false;
    loading = true;
    valorProduto: number;
    produtos: any;
    formulario: FormGroup;
    formularioEdit: FormGroup;
    negociacaoProdutos: NegociacaoProduto;
    negociacaoProdutoIndex: number;
    @ViewChild('dt') table: Table;
    exbindoFormularioEdicao = false;
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
                this.produtos = produto.map(p => ({ label: p.nome, value: p.id, valor: p.valor }));
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
            produto: this.formBuilder.group({
                id: [null, Validators.required]
            }),
            negociacao: this.formBuilder.group({
                id: [this.idNeg],
            }),
        });
    }

    configurarFormularioEdit() {
        this.formularioEdit = this.formBuilder.group({
            id: [],
            quantidade: [null, Validators.required],
            reincidencia: ['UNICO', Validators.required],
            temDesconto: [],
            tipoDesconto: ['PORCENTAGEM'],
            desconto: [],
            valor: [null, Validators.required],
            produto: this.formBuilder.group({
                id: [null, Validators.required]
            }),
            negociacao: this.formBuilder.group({
                id: [this.idNeg],
            }),
        });
    }

    /*     limpar() {
            this.formulario.reset();
            setTimeout(function() {
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

    salvar() {
        this.negociacaoProdutoService.adicionarVenda(this.formulario.value)
            .then(negociacaoAdicionado => {
                this.carregarNegociacao(this.idNeg);
                this.configurarFormulario();
                this.carregarProduto();
                this.qtd = 1;
                this.messageService.add({ severity: 'success', detail: 'Negociacao adicionado com sucesso!' });
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    valor(produtoSelecionado: number): number{
        this.produtos.forEach(produto => {
            if (produto.value === produtoSelecionado) {
                this.valorProduto = produto.valor;
            }
        });
        return this.valorProduto;
    }

    carregarNegociacaoProdutoId(id: number) {
        this.negociacaoProdutoService.buscarPorCodigo(id)
            .then(negociacao => {
                this.formularioEdit.patchValue(negociacao);
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    prepararEdicao(negociacaoProduto: NegociacaoProduto) {
        this.configurarFormularioEdit();
        this.carregarNegociacaoProdutoId(negociacaoProduto.id);
        this.exbindoFormularioEdicao = true;
    }

    salvarNegociacaoProduto() {
        this.negociacaoProdutoService.atualizar(this.formularioEdit.value)
            .then(negociacaoProduto => {
                this.exbindoFormularioEdicao = false;
                this.carregarNegociacao(this.idNeg);

                this.carregarProduto();
                /* this.formularioEdit.patchValue(negociacaoProduto); */
                this.messageService.add({ severity: 'success', detail: 'Negociacao Alterada com sucesso!' });
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

}
