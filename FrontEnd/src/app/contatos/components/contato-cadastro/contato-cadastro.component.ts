import { Title } from '@angular/platform-browser';
import { ErrorHandlerService } from './../../../core/error-handler.service';
import { MessageService } from 'primeng/api';
import { ContatoService } from './../../services/contato.service';
import { Contato } from './../../models/contato.models';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-contato-cadastro',
  templateUrl: './contato-cadastro.component.html',
  styleUrls: ['./contato-cadastro.component.css']
})
export class ContatoCadastroComponent implements OnInit {

  contato = new Contato();
  estados: any[];
  cidades: any[];
  estadoSelecionado: number;

  constructor(
    private contatoService: ContatoService,
    private messageService: MessageService,
    private errorHandler: ErrorHandlerService,
    private route: ActivatedRoute,
    private router: Router,
    private title: Title
  ) { }

  ngOnInit() {
    const idContato = this.route.snapshot.params['id'];

    this.title.setTitle('Nova Contato');

    if (idContato) {
      this.carregarContato(idContato);
    }
  }

  get editando() {
    return Boolean(this.contato.id)
  }

  carregarContato(id: number) {
    this.contatoService.buscarPorId(id)
      .then(contato => {
        this.contato = contato;

        this.atualizarTituloEdicao();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  salvar(form) {
    if (this.editando) {
      this.atualizarContato(form);
    } else {
      this.adicionarContato(form);
    }
  }

  adicionarContato(form) {
    this.contatoService.adicionar(this.contato)
      .then(contatoAdicionada => {
        this.messageService.add({ severity: 'success', detail: 'contato adicionada com sucesso!' });
        this.router.navigate(['/contatos', contatoAdicionada.id]);
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  atualizarContato(form) {
    this.contatoService.atualizar(this.contato)
      .then(contato => {
        this.contato = contato;

        this.messageService.add({ severity: 'success', detail: 'Contato alterada com sucesso!' });
        this.atualizarTituloEdicao();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  limpar(form) {
    form.reset();

    setTimeout(function() {
      this.contato = new Contato();
    }.bind(this), 1);

    this.router.navigate(['/contatos/novo']);
  }

  atualizarTituloEdicao() {
    this.title.setTitle(`Edição de contato: ${this.contato.nome}`);
  }
}
