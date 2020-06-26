import { Tarefa } from './../models/tarefa.models';
import { Estagio } from './../../estagioNegociacao/models/estagio-negociacao.models';
import { HttpParams } from '@angular/common/http';
import { environment } from './../../../environments/environment';
import { MoneyHttp } from './../../usuarios/money-http';
import { Injectable } from '@angular/core';

export class TarefaFiltro {
  assunto: string;
  pagina = 0;
  itensPorPagina = 5;
}

@Injectable({
  providedIn: 'root'
})
export class TarefasService {
  
  tarefaUrl: string;

  constructor(private http: MoneyHttp) {
    this.tarefaUrl = `${environment.apiUrl}/tarefas`;
  }
  listarTodas(): Promise<any> {
    return this.http.get<any>(this.tarefaUrl)
      .toPromise()
      .then(response => response.content);
  }

  pesquisar(filtro: TarefaFiltro): Promise<any> {
    let params = new HttpParams({
      fromObject: {
        page: filtro.pagina.toString(),
        size: filtro.itensPorPagina.toString()
      }
    });
    if (filtro.assunto) {
      params = params.append('nome', filtro.assunto);
    }
    return this.http.get<any>(`${this.tarefaUrl}`, { params })
      .toPromise()
      .then(response => {
        const tarefas = response.content;
        const resultado = {
          tarefas,
          total: response.totalElements
        };
        return resultado;
      })
  }

  excluir(id: number): Promise<void> {
    return this.http.delete(`${this.tarefaUrl}/${id}`)
      .toPromise()
      .then(() => null);
  }

  adicionar(tarefa: Tarefa): Promise<Tarefa> {
    return this.http.post<Tarefa>(this.tarefaUrl, tarefa)
      .toPromise();
  }

  atualizar(tarefa: Tarefa): Promise<Tarefa> {
    return this.http.put<Tarefa>(`${this.tarefaUrl}/${tarefa.id}`, tarefa)
      .toPromise();
  }

  buscarPorCodigo(id: number): Promise<Tarefa> {
    return this.http.get<Tarefa>(`${this.tarefaUrl}/${id}`)
      .toPromise();
  }

/*   private converterStringsParaDatas(lancamentos: Tarefa[]) {
    for (const lancamento of lancamentos) {
      lancamento.dataVencimento = moment(lancamento.dataVencimento,
        'YYYY-MM-DD').toDate();

      if (lancamento.dataPagamento) {
        lancamento.dataPagamento = moment(lancamento.dataPagamento,
          'YYYY-MM-DD').toDate();
      }
    }
  } */

}
