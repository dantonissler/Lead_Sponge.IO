import { Title } from '@angular/platform-browser';
import { ConfirmationService, MessageService, LazyLoadEvent } from 'primeng/api';
import { ErrorHandlerService } from './../../../core/error-handler.service';
import { TarefaFiltro, TarefasService } from './../../services/tarefas.service';
import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-tarefas-pesquisa',
  templateUrl: './tarefas-pesquisa.component.html',
  styleUrls: ['./tarefas-pesquisa.component.scss']
})
export class TarefasPesquisaComponent implements OnInit {

  totalRegistros = 0;
  filtro = new TarefaFiltro();
  tarefas = [];
  loading = true;
  @ViewChild('tabela', { static: true }) grid;

  constructor(
    private tarefasService: TarefasService,
    private errorHandler: ErrorHandlerService,
    private confirmation: ConfirmationService,
    private messageService: MessageService,
    private title: Title
  ) { }

  ngOnInit( ): void {
    this.title.setTitle('Pesquisa de Tarefa');
  }

  pesquisar(pagina = 0) {
    this.filtro.pagina = pagina;
    this.tarefasService.pesquisar(this.filtro)
      .then(resultado => {
        this.totalRegistros = resultado.total;
        this.tarefas = resultado.tarefas;
        this.loading = false;
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  aoMudarPagina(event: LazyLoadEvent) {
    this.filtro.itensPorPagina = event.rows;
    const pagina = event.first / event.rows;
    this.pesquisar(pagina);
  }

  confirmarExclusao(tarefa: any) {
    this.confirmation.confirm({
      message: 'Tem certeza que deseja excluir?',
      accept: () => {
        this.excluir(tarefa);
      }
    });
  }

  excluir(tarefa: any) {
    this.tarefasService.excluir(tarefa.id)
      .then(() => {
        if (this.grid.first === 0) {
          this.pesquisar();
        } else {
          this.grid.first = 0;
          this.pesquisar();
        }
        this.messageService.add({ severity: 'success', detail: 'Tarefa excluída com sucesso!' });
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

}
