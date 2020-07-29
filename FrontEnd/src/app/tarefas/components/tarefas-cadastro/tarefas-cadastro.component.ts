import { NegociacoesService } from './../../../negociacoes/services/negociacoes.service';
import { UsuarioService } from './../../../usuarios/services/usuario.service';
import { ClienteService } from './../../../clientes/services/cliente.service';
import { Title } from '@angular/platform-browser';
import { ErrorHandlerService } from './../../../core/error-handler.service';
import { MessageService } from 'primeng/api';
import { Tarefa } from './../../models/tarefa.models';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { TarefasService } from '../../services/tarefas.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-tarefas-cadastro',
  templateUrl: './tarefas-cadastro.component.html',
  styleUrls: ['./tarefas-cadastro.component.scss']
})
export class TarefasCadastroComponent implements OnInit {

  tipos = [
    { label: 'E-mail', value: 'EMAIL' },
    { label: 'Reunião', value: 'REUNIAO' },
    { label: 'Visita', value: 'VISITA' },
    { label: 'Tarefa', value: 'TAREFA' },
    { label: 'Almoço', value: 'ALMOCO' }
  ];

  formulario: FormGroup;
  tarefas = new Tarefa();
  cliente = [];
  usuario = [];
  negociacao = [];

  constructor(
    private tarefasService: TarefasService,
    private clienteService: ClienteService,
    private usuarioService: UsuarioService,
    private negociacoesService: NegociacoesService,
    private messageService: MessageService,
    private errorHandler: ErrorHandlerService,
    private route: ActivatedRoute,
    private router: Router,
    private title: Title,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.configurarFormulario();
    const idTarefa = this.route.snapshot.params['id'];

    this.title.setTitle('Novo Tarefa');

    if (idTarefa) {
      this.carregarTarefa(idTarefa);
    }
    this.carregarCliente();
    this.carregarUsuario();
    this.carregarNegociacao();
  }
  get editando() {
    return Boolean(this.formulario.get('id').value);
  }

  carregarTarefa(id: number) {
    this.tarefasService.buscarPorCodigo(id)
      .then(tarefa => {
        /* console.log(tarefa.horaMarcada); */
        this.formulario.patchValue(tarefa);
        this.atualizarTituloEdicao();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  carregarCliente() {
    this.clienteService.listarTodas()
      .then(cliente => {
        this.cliente = cliente
          .map(p => ({ label: p.nome, value: p.id }));
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  carregarUsuario() {
    this.usuarioService.listarTodas()
      .then(usuario => {
        this.usuario = usuario
          .map(p => ({ label: p.nomeCompleto, value: p.id }));
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  carregarNegociacao() {
    this.negociacoesService.listarTodas()
      .then(negociaccao => {
        this.negociacao = negociaccao
          .map(p => ({ label: p.nome, value: p.id }));
      })
      .catch(erro => this.errorHandler.handle(erro));
  }


  salvar() {
    if (this.editando) {
      this.atualizarTarefa();
    } else {
      this.adicionarTarefa();
    }
  }
  adicionarTarefa() {
    this.tarefasService.adicionar(this.formulario.value)
      .then(tarefaAdicionado => {
        this.messageService.add({ severity: 'success', detail: 'Tarefa adicionado com sucesso!' });
        this.router.navigate(['/tarefas', tarefaAdicionado.id]);
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  atualizarTarefa() {
    this.tarefasService.atualizar(this.formulario.value)
      .then(tarefa => {
        this.formulario.patchValue(tarefa);

        this.messageService.add({ severity: 'success', detail: 'Tarefa alterado com sucesso!' });
        this.atualizarTituloEdicao();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  atualizarTituloEdicao() {
    this.title.setTitle(`Edição da tarefa: ${this.tarefas.assunto}`);
  }

  configurarFormulario() {
    this.formulario = this.formBuilder.group({
      id: [],
      tipo: [ 'EMAIL', Validators.required ],
      assunto: [null, [this.validarObrigatoriedade, this.validarTamanhoMinimo(4)]],
      descricao: [],
      horaMarcada: [ null, Validators.required ],
      cliente: this.formBuilder.group({
        id: [null, Validators.required],
        nome: []
      }),
      usuario: this.formBuilder.group({
        id: [null, Validators.required],
        nomeCompleto: []
      }),
      negociacao: this.formBuilder.group({
        id: [null, Validators.required],
        nome: []
      }),
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
      this.tarefa = new Tarefa();
    }.bind(this), 1);
    this.router.navigate(['/tarefas/novo']);
  }

}