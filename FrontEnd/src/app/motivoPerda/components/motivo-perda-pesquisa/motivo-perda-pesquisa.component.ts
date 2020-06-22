import { MotivoPerdaFiltro, MotivoPerdaService } from './../../services/motivo-perda.service';
import { Title } from '@angular/platform-browser';
import { ErrorHandlerService } from './../../../core/error-handler.service';
import { MessageService, ConfirmationService, LazyLoadEvent } from 'primeng/api';
import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-motivo-perda-pesquisa',
  templateUrl: './motivo-perda-pesquisa.component.html',
  styleUrls: ['./motivo-perda-pesquisa.component.css']
})
export class MotivoPerdaPesquisaComponent implements OnInit {

  totalRegistros = 0;
  filtro = new MotivoPerdaFiltro();
  motivoPerdas = [];
  @ViewChild('tabela', { static: true }) grid;

  constructor(
    private motivoPerdaService: MotivoPerdaService,
    private errorHandler: ErrorHandlerService,
    private confirmation: ConfirmationService,
    private messageService: MessageService,
    private title: Title
  ) { }

  ngOnInit(): void {
    this.title.setTitle('Pesquisa de Motivo da perda');
  }

  pesquisar(pagina = 0) {
    this.filtro.pagina = pagina;
    this.motivoPerdaService.pesquisar(this.filtro)
      .then(resultado => {
        this.totalRegistros = resultado.total;
        this.motivoPerdas = resultado.motivoPerdas;
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  aoMudarPagina(event: LazyLoadEvent) {
    const pagina = event.first / event.rows;
    this.pesquisar(pagina);
  }

  confirmarExclusao(motivoPerda: any) {
    this.confirmation.confirm({
      message: 'Tem certeza que deseja excluir?',
      accept: () => {
        this.excluir(motivoPerda);
      }
    });
  }

  excluir(motivoPerda: any) {
    this.motivoPerdaService.excluir(motivoPerda.id)
      .then(() => {
        if (this.grid.first === 0) {
          this.pesquisar();
        } else {
          this.grid.first = 0;
          this.pesquisar();
        }
        this.messageService.add({ severity: 'success', detail: 'Motivo da perda excluída com sucesso!' });
      })
      .catch(erro => this.errorHandler.handle(erro));
  }
}
