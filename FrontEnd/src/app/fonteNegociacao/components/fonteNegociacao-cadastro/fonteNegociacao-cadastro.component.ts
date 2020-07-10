import { Title } from '@angular/platform-browser';
import { ErrorHandlerService } from '../../../core/error-handler.service';
import { MessageService } from 'primeng/api';
import { FontesService } from '../../services/fontes.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { Fonte } from '../../models/fonte.models';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-fonteNegociacao-cadastro',
  templateUrl: './fonteNegociacao-cadastro.component.html',
  styleUrls: ['./fonteNegociacao-cadastro.component.scss']
})
export class FontesCadastroComponent implements OnInit {

  formulario: FormGroup;
  fontes = new Fonte();
  
  constructor(
    private fontesService: FontesService,
    private messageService: MessageService,
    private errorHandler: ErrorHandlerService,
    private route: ActivatedRoute,
    private router: Router,
    private title: Title,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.configurarFormulario();
    const idFonte = this.route.snapshot.params['id'];

    this.title.setTitle('Novo Fonte');
    
    if (idFonte) {
      this.carregarFonte(idFonte);
    }
  }
  get editando() {
    return Boolean(this.formulario.get('id').value);
  }

  carregarFonte(id: number) {
    this.fontesService.buscarPorCodigo(id)
      .then(fonte => {
        this.formulario.patchValue(fonte);
        this.atualizarTituloEdicao();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }
  salvar() {
    if (this.editando) {
      this.atualizarFonte();
    } else {
      this.adicionarFonte();
    }
  }
  adicionarFonte() {
    this.fontesService.adicionar(this.formulario.value)
      .then(fonteAdicionado => {
        this.messageService.add({ severity: 'success', detail: 'Fonte adicionado com sucesso!' });
        this.router.navigate(['/fontes', fonteAdicionado.id]);
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  atualizarFonte() {
    this.fontesService.atualizar(this.formulario.value)
      .then(fonte => {
        this.formulario.patchValue(fonte);

        this.messageService.add({ severity: 'success', detail: 'Fonte alterado com sucesso!' });
        this.atualizarTituloEdicao();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  atualizarTituloEdicao() {
    this.title.setTitle(`Edição da fonte: ${this.fontes.nome}`);
  }

  configurarFormulario() {
    this.formulario = this.formBuilder.group({
      id:[],
      nome: [null, [ this.validarObrigatoriedade, this.validarTamanhoMinimo(4) ]],
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
      this.fonte = new Fonte();
    }.bind(this), 1);
    this.router.navigate(['/fontes/novo']);
  }
}
