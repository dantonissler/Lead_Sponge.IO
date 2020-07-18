import { Title } from '@angular/platform-browser';
import { ConfirmationService, MessageService, LazyLoadEvent } from 'primeng/api';
import { ErrorHandlerService } from './../../../core/error-handler.service';
import { ClienteFiltro, ClienteService } from './../../services/cliente.service';
import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-cliente-pesquisa',
  templateUrl: './cliente-pesquisa.component.html',
  styleUrls: ['./cliente-pesquisa.component.scss']
})
export class ClientePesquisaComponent implements OnInit {

  totalRegistros = 0;
  filtro = new ClienteFiltro();
  clientes = [];
  loading: boolean = true;
  @ViewChild('tabela', { static: true }) grid;

  constructor(
    private ClienteService: ClienteService,
    private errorHandler: ErrorHandlerService,
    private confirmation: ConfirmationService,
    private messageService: MessageService,
    private title: Title
  ) { }

  ngOnInit(): void {
    this.title.setTitle('Pesquisa de Cliente');
  }

  pesquisar(pagina = 0) {
    this.filtro.pagina = pagina;
    this.ClienteService.pesquisar(this.filtro)
      .then(resultado => {
        this.totalRegistros = resultado.total;
        this.clientes = resultado.clientes;
        this.loading = false;
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  aoMudarPagina(event: LazyLoadEvent) {
    this.filtro.itensPorPagina = event.rows;
    const pagina = event.first / event.rows;
    this.pesquisar(pagina);
  }

  confirmarExclusao(cliente: any) {
    this.confirmation.confirm({
      message: 'Tem certeza que deseja excluir?',
      accept: () => {
        this.excluir(cliente);
      }
    });
  }

  excluir(clientes: any) {
    this.ClienteService.excluir(clientes.id)
      .then(() => {
        if (this.grid.first === 0) {
          this.pesquisar();
        } else {
          this.grid.first = 0;
          this.pesquisar();
        }
        this.messageService.add({ severity: 'success', detail: 'Clientes excluída com sucesso!' });
      })
      .catch(erro => this.errorHandler.handle(erro));
  }
}
