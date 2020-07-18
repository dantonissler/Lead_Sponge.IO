import { Title } from '@angular/platform-browser';
import { ConfirmationService, MessageService, LazyLoadEvent } from 'primeng/api';
import { ErrorHandlerService } from './../../../core/error-handler.service';
import { ContatoFiltro, ContatoService } from './../../services/contato.service';
import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-contato-pesquisa',
  templateUrl: './contato-pesquisa.component.html',
  styleUrls: ['./contato-pesquisa.component.scss']
})
export class ContatoPesquisaComponent implements OnInit {

  totalRegistros = 0;
  filtro = new ContatoFiltro();
  contatos = [];
  loading: boolean = true;
  @ViewChild('tabela', { static: true }) grid;

  constructor(
    private contatoService: ContatoService,
    private errorHandler: ErrorHandlerService,
    private confirmation: ConfirmationService,
    private messageService: MessageService,
    private title: Title
  ) { }

  ngOnInit( ): void {
    this.title.setTitle('Pesquisa de Contatos');
  }

  pesquisar(pagina = 0) {
    this.filtro.pagina = pagina;
    this.contatoService.pesquisar(this.filtro)
      .then(resultado => {
        this.totalRegistros = resultado.total;
        this.contatos = resultado.contatos;
        this.loading = false;
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  aoMudarPagina(event: LazyLoadEvent) {
    this.filtro.itensPorPagina = event.rows;
    const pagina = event.first / event.rows;
    this.pesquisar(pagina);
  }

  confirmarExclusao(contatos: any) {
    this.confirmation.confirm({
      message: 'Tem certeza que deseja excluir?',
      accept: () => {
        this.excluir(contatos);
      }
    });
  }

  excluir(contatos: any) {
    this.contatoService.excluir(contatos.id)
      .then(() => {
        if (this.grid.first === 0) {
          this.pesquisar();
        } else {
          this.grid.first = 0;
          this.pesquisar();
        }
        this.messageService.add({ severity: 'success', detail: 'Contato excluída com sucesso!' });
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

}
