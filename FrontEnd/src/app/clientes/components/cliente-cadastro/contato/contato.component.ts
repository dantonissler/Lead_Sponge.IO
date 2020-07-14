import { FormGroup, FormBuilder } from '@angular/forms';
import { Contato } from './../../../../contatos/models/contato.models';
import { Component, OnInit, Input } from '@angular/core';

@Component({
    selector: 'app-contato',
    templateUrl: './contato.component.html',
    styleUrls: ['./contato.component.scss']
})
export class ContatoComponent implements OnInit {

    @Input() contatos: Array<Contato>;
    contato: Contato;
    exbindoFormularioContato = false;
    contatoIndex: number;

    constructor() { }

    ngOnInit() {
    }


    prepararNovoContato() {
        this.exbindoFormularioContato = true;
        this.contato = new Contato();
        this.contatoIndex = this.contatos.length;
    }

    prepararEdicaoContato(contato: Contato, index: number) {
        this.contato = this.clonarContato(contato);
        this.exbindoFormularioContato = true;
        this.contatoIndex = index;
    }

    confirmarContato(frm) {
        this.contatos[this.contatoIndex] = this.clonarContato(this.contato);
        this.exbindoFormularioContato = false;
        frm.reset();
    }

    removerContato(index: number) {
        this.contatos.splice(index, 1);
    }

    clonarContato(contato: Contato): Contato {
        return new Contato(contato.id, contato.nome, contato.cargo);
    }

    get editando() {
        return this.contato && this.contato.id;
    }

}
