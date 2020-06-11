import { Title } from '@angular/platform-browser';
import { ErrorHandlerService } from './../../../core/error-handler.service';
import { MessageService } from 'primeng/api';
import { MotivoPerdaService } from './../../services/motivo-perda.service';
import { Perda } from './../../models/motivo-perda.models';
import { FormControl, FormBuilder, FormGroup } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-motivo-perda-cadastro',
  templateUrl: './motivo-perda-cadastro.component.html',
  styleUrls: ['./motivo-perda-cadastro.component.css']
})
export class MotivoPerdaCadastroComponent implements OnInit {

  formulario: FormGroup;
  motivoPerdas = new Perda();
  
  constructor(
    private motivoPerdaService: MotivoPerdaService,
    private messageService: MessageService,
    private errorHandler: ErrorHandlerService,
    private route: ActivatedRoute,
    private router: Router,
    private title: Title,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.configurarFormulario();
    const idMotivoPerda = this.route.snapshot.params['id'];

    this.title.setTitle('Novo Motivo de Perda');
    
    if (idMotivoPerda) {
      this.carregarMotivoPerda(idMotivoPerda);
    }
  }
  get editando() {
    return Boolean(this.formulario.get('id').value);
  }

  carregarMotivoPerda(id: number) {
    this.motivoPerdaService.buscarPorCodigo(id)
      .then(motivoPerda => {
        this.formulario.patchValue(motivoPerda);
        this.atualizarTituloEdicao();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }
  salvar() {
    if (this.editando) {
      this.atualizarMotivoPerda();
    } else {
      this.adicionarMotivoPerda();
    }
  }
  adicionarMotivoPerda() {
    this.motivoPerdaService.adicionar(this.formulario.value)
      .then(fonteAdicionado => {
        this.messageService.add({ severity: 'success', detail: 'Motivo de perda adicionado com sucesso!' });
        this.router.navigate(['/motivoperda', fonteAdicionado.id]);
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  atualizarMotivoPerda() {
    this.motivoPerdaService.atualizar(this.formulario.value)
      .then(fonteAdicionado => {
        this.formulario.patchValue(fonteAdicionado);

        this.messageService.add({ severity: 'success', detail: 'Motivo de perda alterado com sucesso!' });
        this.atualizarTituloEdicao();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  atualizarTituloEdicao() {
    this.title.setTitle(`Edição do Motivo de perda: ${this.motivoPerdas.nome}`);
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
      this.motivoPerdas = new Perda();
    }.bind(this), 1);
    this.router.navigate(['/motivoperda/novo']);
  }
}
