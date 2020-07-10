import { Title } from '@angular/platform-browser';
import { ErrorHandlerService } from './../../../core/error-handler.service';
import { MessageService } from 'primeng/api';
import { SegmentosService } from './../../services/segmentos.service';
import { FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Segmento } from '../../models/segmento.models';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-segmentos-cadastro',
  templateUrl: './segmentos-cadastro.component.html',
  styleUrls: ['./segmentos-cadastro.component.scss']
})
export class SegmentosCadastroComponent implements OnInit {

  formulario: FormGroup;
  segmentos = new Segmento();
  
  constructor(
    private segmentosService: SegmentosService,
    private messageService: MessageService,
    private errorHandler: ErrorHandlerService,
    private route: ActivatedRoute,
    private router: Router,
    private title: Title,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.configurarFormulario();
    const idSegmento = this.route.snapshot.params['id'];

    this.title.setTitle('Novo Segmento');
    
    if (idSegmento) {
      this.carregarSegmento(idSegmento);
    }
  }
  get editando() {
    return Boolean(this.formulario.get('id').value);
  }

  carregarSegmento(id: number) {
    this.segmentosService.buscarPorCodigo(id)
      .then(segmento => {
        this.formulario.patchValue(segmento);
        this.atualizarTituloEdicao();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }
  salvar() {
    if (this.editando) {
      this.atualizarSegmento();
    } else {
      this.adicionarSegmento();
    }
  }
  adicionarSegmento() {
    this.segmentosService.adicionar(this.formulario.value)
      .then(segmentoAdicionado => {
        this.messageService.add({ severity: 'success', detail: 'Segmento adicionado com sucesso!' });
        this.router.navigate(['/segmentos', segmentoAdicionado.id]);
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  atualizarSegmento() {
    this.segmentosService.atualizar(this.formulario.value)
      .then(segmento => {
        this.formulario.patchValue(segmento);

        this.messageService.add({ severity: 'success', detail: 'Segmento alterado com sucesso!' });
        this.atualizarTituloEdicao();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  atualizarTituloEdicao() {
    this.title.setTitle(`Edição da segmento: ${this.segmentos.nome}`);
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
      this.segmento = new Segmento();
    }.bind(this), 1);
    this.router.navigate(['/segmentos/novo']);
  }
}
