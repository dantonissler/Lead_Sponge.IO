import { Produto } from './../../models/produto.models';
import { Title } from '@angular/platform-browser';
import { MessageService } from 'primeng/api';
import { ErrorHandlerService } from './../../../core/error-handler.service';
import { ProdutosService } from './../../services/produtos.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-produtos-cadastro',
  templateUrl: './produtos-cadastro.component.html',
  styleUrls: ['./produtos-cadastro.component.css']
})
export class ProdutosCadastroComponent implements OnInit {

  valorInicial: number = 0.00;
  formulario: FormGroup;
  produtos = new Produto();
  
  constructor(
    private produtosService: ProdutosService,
    private messageService: MessageService,
    private errorHandler: ErrorHandlerService,
    private route: ActivatedRoute,
    private router: Router,
    private title: Title,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.configurarFormulario();
    const idProduto = this.route.snapshot.params['id'];

    this.title.setTitle('Novo Produto');
    
    if (idProduto) {
      this.carregarProduto(idProduto);
    }
  }
  get editando() {
    return Boolean(this.formulario.get('id').value);
  }

  carregarProduto(id: number) {
    this.produtosService.buscarPorCodigo(id)
      .then(produto => {
        this.formulario.patchValue(produto);
        this.atualizarTituloEdicao();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }
  salvar() {
    if (this.editando) {
      this.atualizarProduto();
    } else {
      this.adicionarProduto();
    }
  }
  adicionarProduto() {
    this.produtosService.adicionar(this.formulario.value)
      .then(fonteAdicionado => {
        this.messageService.add({ severity: 'success', detail: 'Produto adicionado com sucesso!' });
        this.router.navigate(['/produtos', fonteAdicionado.id]);
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  atualizarProduto() {
    this.produtosService.atualizar(this.formulario.value)
      .then(fonteAdicionado => {
        this.formulario.patchValue(fonteAdicionado);

        this.messageService.add({ severity: 'success', detail: 'Produto alterado com sucesso!' });
        this.atualizarTituloEdicao();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  atualizarTituloEdicao() {
    this.title.setTitle(`Edição do Produto: ${this.produtos.nome}`);
  }

  configurarFormulario() {
    this.formulario = this.formBuilder.group({
      id:[],
      nome: [null, [ this.validarObrigatoriedade, this.validarTamanhoMinimo(4) ]],
      descricao: [],
      valor: [null, [ this.validarObrigatoriedade]],
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
    setTimeout(function() {
      this.produtos = new Produto();
    }.bind(this), 1);
    this.router.navigate(['/produtos/novo']);
  }
}
