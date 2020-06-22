import { Email } from './../../models/email.models';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-contato-cadastro-email',
  templateUrl: './contato-cadastro-email.component.html',
  styleUrls: ['./contato-cadastro-email.component.css']
})
export class ContatoCadastroEmailComponent implements OnInit {

  @Input() emails: Array<Email>;
  email: Email;
  exbindoFormularioEmail = false;
  emailIndex: number;

  constructor() { }

  ngOnInit() {
  }

  prepararNovoEmail() {
    this.exbindoFormularioEmail = true;
    this.email = new Email();
    this.emailIndex = this.emails.length;
  }

  prepararEdicaoEmail(email: Email, index: number) {
    this.email = this.clonarEmail(email);
    this.exbindoFormularioEmail = true;
    this.emailIndex = index;
  }

  confirmarEmail(frm) {
    this.emails[this.emailIndex] = this.clonarEmail(this.email);

    this.exbindoFormularioEmail = false;

    frm.reset();
  }

  removerEmail(index: number) {
    this.emails.splice(index, 1);
  }
  clonarEmail(email: Email): Email {
    return new Email(email.id, email.email);
  }

  get editando() {
    return this.email && this.email.id;
  }
}
