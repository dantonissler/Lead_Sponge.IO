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

    formulario: FormGroup;
    produtos: [];

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
            .then(cliente => {
                this.produtos = cliente
                    .map(p => ({ label: p.nome, value: p.id }));
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
            produto: this.formBuilder.group({
                id: [null, Validators.required],
                nome: []
            }),
            negociação: this.formBuilder.group({
                id: [this.route.snapshot.params['id']],
            }),
        })
    }

}
