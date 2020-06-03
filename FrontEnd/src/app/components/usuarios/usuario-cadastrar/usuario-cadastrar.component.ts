import { FormControl } from '@angular/forms';
import { Usuario } from './../../../models/usuario.model';
import { ErrorHandlerService } from './../../../services/error-handler.service';
import { MessageService } from 'primeng/api';
import { UsuarioService } from './../../../services/usuario.service';
import { Title } from '@angular/platform-browser';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-usuario-cadastrar',
  templateUrl: './usuario-cadastrar.component.html',
  styleUrls: ['./usuario-cadastrar.component.css']
})
export class UsuarioCadastrarComponent implements OnInit {

  usuario = new Usuario();
  roles: any[];

  constructor(
    private usuarioService: UsuarioService,
    private messageService: MessageService,
    private errorHandler: ErrorHandlerService,
    private route: ActivatedRoute,
    private router: Router,
    private title: Title
  ) { }

  ngOnInit() {
    const idUsuario = this.route.snapshot.params['id'];

    this.title.setTitle('Novo usuario');

    if (idUsuario) {
      this.carregarPessoa(idUsuario);
    }
  }

  get editando() {
    return Boolean(this.usuario.id)
  }

  carregarPessoa(id: number) {
    this.usuarioService.buscarPorCodigo(id)
      .then(usuario => {
        this.usuario = usuario;
        this.atualizarTituloEdicao();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  salvar(form: FormControl) {
    if (this.editando) {
      this.atualizarUsuario(form);
    } else {
      this.adicionarUsuario(form);
    }
  }

  adicionarUsuario(form: FormControl) {
    this.usuarioService.adicionar(this.usuario)
      .then(usuarioAdicionado => {
        this.messageService.add({ severity: 'success', detail: 'Usuario adicionado com sucesso!' });
        this.router.navigate(['/pessoas', usuarioAdicionado.id]);
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  atualizarUsuario(form: FormControl) {
    this.usuarioService.atualizar(this.usuario)
      .then(usuario => {
        this.usuario = usuario;

        this.messageService.add({ severity: 'success', detail: 'Pessoa alterada com sucesso!' });
        this.atualizarTituloEdicao();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  atualizarTituloEdicao() {
    this.title.setTitle(`Edição de usuario: ${this.usuario.nomeCompleto}`);
  }
}
