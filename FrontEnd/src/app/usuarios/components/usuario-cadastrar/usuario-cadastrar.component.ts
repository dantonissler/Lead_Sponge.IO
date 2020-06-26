import { UsuarioService } from '../../services/usuario.service';
import { RoleService } from '../../services/role.service';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Usuario } from '../../models/usuario.model';
import { ErrorHandlerService } from '../../../core/error-handler.service';

import { MessageService } from 'primeng/api';

import { Title } from '@angular/platform-browser';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-usuario-cadastrar',
  templateUrl: './usuario-cadastrar.component.html',
  styleUrls: ['./usuario-cadastrar.component.css'],
})
export class UsuarioCadastrarComponent implements OnInit {

  showConfPass: boolean = false;
  iconConf: string = 'pi pi-eye';
  showPass: boolean = false;
  icon: string = 'pi pi-eye';
  formulario: FormGroup;
  checked: boolean = true;
  usuario = new Usuario();
  
  roles = [];

  constructor(
    private usuarioService: UsuarioService,
    private roleService: RoleService,
    private messageService: MessageService,
    private errorHandler: ErrorHandlerService,
    private route: ActivatedRoute,
    private router: Router,
    private title: Title,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.configurarFormulario();
    const idUsuario = this.route.snapshot.params['id'];
    this.title.setTitle('Novo usuario');
    this.carregarRole() 
    if (idUsuario) {
      this.carregarUsuario(idUsuario);
    }
  }
  
  get editando() {
    return Boolean(this.formulario.get('id').value);
  }

  carregarUsuario(id: number) {
    this.usuarioService.buscarPorCodigo(id)
      .then(usuario => {
        usuario.password = '';
        usuario.confirmarPassword = '';
        this.formulario.patchValue(usuario);
        this.atualizarTituloEdicao();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  carregarRole() {
    this.roleService.listarTodas()
    .then(roles => {
      this.roles = roles.map(c => ({ nome: c.nome, id: c.id }));
    })
    .catch(erro => this.errorHandler.handle(erro));
  }

  salvar() {
    if (this.editando) {
      this.atualizarUsuario();
    } else {
      this.adicionarUsuario();
    }
  }

  adicionarUsuario() {
    this.usuarioService.adicionar(this.formulario.value)
      .then(usuarioAdicionado => {
        this.messageService.add({ severity: 'success', detail: 'Usuario adicionado com sucesso!' });
        this.router.navigate(['/usuarios', usuarioAdicionado.id]);
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  atualizarUsuario() {
    this.usuarioService.atualizar(this.formulario.value)
      .then(usuario => {
        this.formulario.patchValue(usuario);
        this.atualizarTituloEdicao();
        this.messageService.add({ severity: 'success', detail: 'Usuario alterado com sucesso!' });
        this.carregarUsuario(usuario.id);
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  atualizarTituloEdicao() {
    this.title.setTitle(`Edição de usuario: ${this.formulario.get('nomeCompleto').value}`);
  }

  configurarFormulario() {
    this.formulario = this.formBuilder.group({
      id:[],
      username: [null, [ this.validarObrigatoriedade, this.validarTamanhoMinimo(4) ]],
      nomeCompleto: [null, [ this.validarObrigatoriedade, this.validarTamanhoMinimo(4) ]],
      email: [null,[this.validarObrigatoriedade, Validators.email]],
      password: [null,[this.validarObrigatoriedade, this.validarTamanhoMinimo(6)]],
      confirmarPassword: [null,[this.validarObrigatoriedade, this.validarTamanhoMinimo(6)]],
      enabled:[],
      roles:[],
    }, {validator: this.validarConfirmaPassword });
  }

  validarConfirmaPassword(formGroup: FormGroup) {
    let password = formGroup.get('password').value;
    let confirmarPassword  = formGroup.get('confirmarPassword').value;
    return password === confirmarPassword ? null : { passwordNotMatch: true };
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
      this.lancamento = new Usuario();
    }.bind(this), 1);
    this.router.navigate(['/usuarios/novo']);
  }
  esconderSenha(){
    this.showPass = !this.showPass;
    if (this.showPass == true) {
      this.icon ='pi pi-eye-slash';
    }else{
      this.icon ='pi pi-eye';
    }
  }
  esconderConfSenha(){
    this.showConfPass = !this.showConfPass;
    if (this.showConfPass == true) {
      this.iconConf ='pi pi-eye-slash';
    }else{
      this.iconConf ='pi pi-eye';
    }
  }
}
