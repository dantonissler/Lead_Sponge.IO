import { Title } from '@angular/platform-browser';
import { ErrorHandlerService } from './../../../core/error-handler.service';
import { MessageService } from 'primeng/api';
import { ClienteService } from './../../services/cliente.service';
import { Cliente } from './../../models/cliente.models';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SegmentosService } from 'src/app/segmentos/services/segmentos.service';

@Component({
  selector: 'app-cliente-cadastro',
  templateUrl: './cliente-cadastro.component.html',
  styleUrls: ['./cliente-cadastro.component.css']
})
export class ClienteCadastroComponent implements OnInit {

  segmentos =[];
  formulario: FormGroup;
  clientes = new Cliente();

  constructor(
    private segmentoService: SegmentosService,
    private clienteService: ClienteService,
    private messageService: MessageService,
    private errorHandler: ErrorHandlerService,
    private route: ActivatedRoute,
    private router: Router,
    private title: Title,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.configurarFormulario();
    const idCliente = this.route.snapshot.params['id'];
    this.title.setTitle('Novo Cliente');
    this.carregarSegmento();
    if (idCliente) {
      this.carregarCliente(idCliente);
    }
  }

  get editando() {
    return Boolean(this.formulario.get('id').value);
  }

  carregarCliente(id: number) {
    this.clienteService.buscarPorCodigo(id)
      .then(cliente => {
        this.formulario.patchValue(cliente);
        this.atualizarTituloEdicao();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  carregarSegmento() {
    return this.segmentoService.listarTodas()
      .then(segmentos => {
        this.segmentos = segmentos.map(c => ({ label: c.nome, value: c.id }));
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  salvar() {
    if (this.editando) {
      this.atualizarCliente();
    } else {
      this.adicionarCliente();
    }
  }

  adicionarCliente() {
    this.clienteService.adicionar(this.formulario.value)
      .then(clienteAdicionado => {
        this.messageService.add({ severity: 'success', detail: 'Cliente adicionado com sucesso!' });
        this.router.navigate(['/clientes', clienteAdicionado.id]);
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  atualizarCliente() {
    this.clienteService.atualizar(this.formulario.value)
      .then(cliente => {
        this.formulario.patchValue(cliente);
        this.messageService.add({ severity: 'success', detail: 'Cliente alterado com sucesso!' });
        this.atualizarTituloEdicao();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  atualizarTituloEdicao() {
    this.title.setTitle(`Edição da Cliente: ${this.clientes.nome}`);
  }

  configurarFormulario() {
    this.formulario = this.formBuilder.group({
      id: [],
      nome: [null, [this.validarObrigatoriedade, this.validarTamanhoMinimo(4)]],
      segmento: this.formBuilder.group({
        id: [],
        nome: []
      }),
      url: [],
      resumo: [],
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
    setTimeout(function () {
      this.lancamento = new Cliente();
    }.bind(this), 1);
    this.router.navigate(['/clientes/novo']);
  }
}