import { FormControl } from '@angular/forms';
import { Telefone } from './../../models/telefone.models';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-contato-cadastro-telefone',
  templateUrl: './contato-cadastro-telefone.component.html',
  styleUrls: ['./contato-cadastro-telefone.component.css']
})
export class ContatoCadastroTelefoneComponent implements OnInit {

  tipos = [
    { label: 'Comercial', tipo: 'COMERCIAL' },
    { label: 'Residencial', tipo: 'RESIDENCIAL' },
    { label: 'Pessoal', tipo: 'PESSOAL' },
    { label: 'Fax', tipo: 'FAX' }
  ];

  @Input() telefones: Array<Telefone>;
  telefone: Telefone;
  exbindoFormularioTelefone = false;
  telefoneIndex: number;

  constructor() { }

  ngOnInit() {
  }

  prepararNovoTelefone() {
    this.exbindoFormularioTelefone = true;
    this.telefone = new Telefone();
    this.telefoneIndex = this.telefones.length;
  }

  prepararEdicaoTelefone(telefone: Telefone, index: number) {
    this.telefone = this.clonarTelefone(telefone);
    this.exbindoFormularioTelefone = true;
    this.telefoneIndex = index;
  }

  confirmarTelefone(frm) {
    this.telefones[this.telefoneIndex] = this.clonarTelefone(this.telefone);

    this.exbindoFormularioTelefone = false;

    frm.reset();
  }

  removerTelefone(index: number) {
    this.telefones.splice(index, 1);
  }

  clonarTelefone(telefone: Telefone): Telefone {
    return new Telefone(telefone.id,
      telefone.numero, telefone.tipo);
  }

  get editando() {
    return this.telefone && this.telefone.id;
  }

}
