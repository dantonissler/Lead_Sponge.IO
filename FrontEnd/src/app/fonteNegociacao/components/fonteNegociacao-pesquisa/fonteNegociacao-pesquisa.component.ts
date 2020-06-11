import { Title } from '@angular/platform-browser';
import { ConfirmationService, MessageService, LazyLoadEvent } from 'primeng/api';
import { ErrorHandlerService } from '../../../core/error-handler.service';
import { FonteFiltro, FontesService } from '../../services/fontes.service';
import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-fonteNegociacao-pesquisa',
  templateUrl: './fonteNegociacao-pesquisa.component.html',
  styleUrls: ['./fonteNegociacao-pesquisa.component.css']
})
export class FontesPesquisaComponent implements OnInit {

  totalRegistros = 0;
  filtro = new FonteFiltro();
  fontes = [];
  @ViewChild('tabela', { static: true }) grid;

  constructor(
    private fontesService: FontesService,
    private errorHandler: ErrorHandlerService,
    private confirmation: ConfirmationService,
    private messageService: MessageService,
    private title: Title
  ) { }

  ngOnInit(): void {
    this.title.setTitle('Pesquisa de Fonte');
  }

  pesquisar(pagina = 0) {
    this.filtro.pagina = pagina;
    this.fontesService.pesquisar(this.filtro)
      .then(resultado => {
        this.totalRegistros = resultado.total;
        this.fontes = resultado.fontes;
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  aoMudarPagina(event: LazyLoadEvent) {
    const pagina = event.first / event.rows;
    this.pesquisar(pagina);
  }

  confirmarExclusao(fonte: any) {
    this.confirmation.confirm({
      message: 'Tem certeza que deseja excluir?',
      accept: () => {
        this.excluir(fonte);
      }
    });
  }

  excluir(fonte: any) {
    this.fontesService.excluir(fonte.id)
      .then(() => {
        if (this.grid.first === 0) {
          this.pesquisar();
        } else {
          this.grid.first = 0;
          this.pesquisar();
        }
        this.messageService.add({ severity: 'success', detail: 'Fonte excluída com sucesso!' });
      })
      .catch(erro => this.errorHandler.handle(erro));
  }
}
