import { Title } from '@angular/platform-browser';
import { ErrorHandlerService } from './../../../core/error-handler.service';
import { MessageService } from 'primeng/api';
import { CampanhasService } from './../../services/campanhas.service';
import { Campanha } from './../../models/campanha.models';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
    selector: 'app-campanhas-cadastro',
    templateUrl: './campanhas-cadastro.component.html',
    styleUrls: ['./campanhas-cadastro.component.scss']
})
export class CampanhasCadastroComponent implements OnInit {

    formulario: FormGroup;
    campanhas = new Campanha();

    constructor(
        private campanhaService: CampanhasService,
        private messageService: MessageService,
        private errorHandler: ErrorHandlerService,
        private route: ActivatedRoute,
        private router: Router,
        private title: Title,
        private formBuilder: FormBuilder
    ) { }

    ngOnInit(): void {
        this.configurarFormulario();
        const idCampanha = this.route.snapshot.params.id;
        this.title.setTitle('Novo Campanha');
        if (idCampanha) {
            this.carregarCampanha(idCampanha);
        }
    }
    get editando() {
        return Boolean(this.formulario.get('id').value);
    }

    carregarCampanha(id: number) {
        this.campanhaService.buscarPorCodigo(id)
            .then(campanha => {
                this.formulario.patchValue(campanha);
                this.atualizarTituloEdicao();
            })
            .catch(erro => this.errorHandler.handle(erro));
    }
    salvar() {
        if (this.editando) {
            this.atualizarCampanha();
        } else {
            this.adicionarCampanha();
        }
    }
    adicionarCampanha() {
        this.campanhaService.adicionar(this.formulario.value)
            .then(campanhaAdicionado => {
                this.messageService.add({ severity: 'success', detail: 'Campanha adicionado com sucesso!' });
                this.router.navigate(['/campanhas', campanhaAdicionado.id]);
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    atualizarCampanha() {
        this.campanhaService.atualizar(this.formulario.value)
            .then(campanha => {
                this.formulario.patchValue(campanha);

                this.messageService.add({ severity: 'success', detail: 'Lançamento alterado com sucesso!' });
                this.atualizarTituloEdicao();
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    atualizarTituloEdicao() {
        this.title.setTitle(`Edição da campanha: ${this.campanhas.nome}`);
    }

    configurarFormulario() {
        this.formulario = this.formBuilder.group({
            id: [],
            nome: [null, [this.validarObrigatoriedade, this.validarTamanhoMinimo(4)]],
            descricao: [],
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
            this.lancamento = new Campanha();
        }.bind(this), 1);
        this.router.navigate(['/campanhas/novo']);
    }
}
