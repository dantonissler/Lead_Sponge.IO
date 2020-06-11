import { Title } from '@angular/platform-browser';
import { Router, ActivatedRoute } from '@angular/router';
import { ErrorHandlerService } from './../../../core/error-handler.service';
import { MessageService } from 'primeng/api';
import { Estagio } from './../../models/estagio-negociacao.models';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { EstagioNegociacaoService } from '../../services/estagio-negociacao.service';

@Component({
  selector: 'app-estagio-negociacao-cadastro',
  templateUrl: './estagio-negociacao-cadastro.component.html',
  styleUrls: ['./estagio-negociacao-cadastro.component.css']
})
export class EstagioNegociacaoCadastroComponent implements OnInit {

  formulario: FormGroup;
  estagios = new Estagio();
  
  constructor(
    private estagioService: EstagioNegociacaoService,
    private messageService: MessageService,
    private errorHandler: ErrorHandlerService,
    private route: ActivatedRoute,
    private router: Router,
    private title: Title,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.configurarFormulario();
    const idEstagio = this.route.snapshot.params['id'];

    this.title.setTitle('Novo Estagio');
    
    if (idEstagio) {
      this.carregarEstagio(idEstagio);
    }
  }
  get editando() {
    return Boolean(this.formulario.get('id').value);
  }

  carregarEstagio(id: number) {
    this.estagioService.buscarPorCodigo(id)
      .then(estagio => {
        this.formulario.patchValue(estagio);
        this.atualizarTituloEdicao();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }
  salvar() {
    if (this.editando) {
      this.atualizarEstagio();
    } else {
      this.adicionarEstagio();
    }
  }
  adicionarEstagio() {
    this.estagioService.adicionar(this.formulario.value)
      .then(estagioAdicionado => {
        this.messageService.add({ severity: 'success', detail: 'Estagio adicionado com sucesso!' });
        this.router.navigate(['/estagios', estagioAdicionado.id]);
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  atualizarEstagio() {
    this.estagioService.atualizar(this.formulario.value)
      .then(estagio => {
        this.formulario.patchValue(estagio);

        this.messageService.add({ severity: 'success', detail: 'Lançamento alterado com sucesso!' });
        this.atualizarTituloEdicao();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  atualizarTituloEdicao() {
    this.title.setTitle(`Edição da estagio: ${this.estagios.nome}`);
  }

  configurarFormulario() {
    this.formulario = this.formBuilder.group({
      id:[],
      nome: [null, [ this.validarObrigatoriedade, this.validarTamanhoMinimo(4) ]],
      apelido: [null, [ this.validarObrigatoriedade, this.validarTamanhoMinimo(1) ]],
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
      this.lancamento = new Estagio();
    }.bind(this), 1);
    this.router.navigate(['/estagios/novo']);
  }

}
