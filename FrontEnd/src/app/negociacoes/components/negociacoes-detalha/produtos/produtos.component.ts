import { Router, ActivatedRoute } from '@angular/router';
import { NegociacoesService } from './../../../services/negociacoes.service';
import { ErrorHandlerService } from './../../../../core/error-handler.service';
import { MessageService } from 'primeng/api';
import { ProdutosService } from './../../../../produtos/services/produtos.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-produtos',
    templateUrl: './produtos.component.html',
    styleUrls: ['./produtos.component.scss']
})
export class ProdutosComponent implements OnInit {

    produtos: [];
    formulario: FormGroup;
    idNeg: number = +this.route.snapshot.params['id'];
    optReincidencia = [{ label: 'Recorrente', value: 'RECORRENTE' }, { label: 'Único', value: 'UNICO' }];
    optTipoDesconto = [{ label: '%', value: 'PORCENTAGEM' }, { label: 'R$', value: 'VALOR' }];
    checked: boolean = false;

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
                this.produtos = produto
                    .map(p => ({ label: p.nome, value: p.id, valor: p.valor }));
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    salvar() {
        this.negociacoesService.adicionar(this.formulario.value)
            .then(negociacaoAdicionado => {
                this.messageService.add({ severity: 'success', detail: 'Negociacao adicionado com sucesso!' });
                this.router.navigate(['/negociacoes', negociacaoAdicionado.id]);
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    configurarFormulario() {
        this.formulario = this.formBuilder.group({
            id: [],
            quantidade: [null, Validators.required],
            reincidencia: ['RECORRENTE', Validators.required],
            temDesconto: [],
            tipoDesconto: ['PORCENTAGEM'],
            desconto: [],
            produto: this.formBuilder.group({
                id: [null, Validators.required]
            }),
            negociação: this.formBuilder.group({
                id: [this.idNeg],
            }),
        })
    }
}
