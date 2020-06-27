import { Negociacao } from './../models/negociacao.models';
import { HttpParams } from '@angular/common/http';
import { MoneyHttp } from './../../usuarios/money-http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

export class NegociacoesFiltro {
  nome: string;
  pagina = 0;
  itensPorPagina = 5;
}

@Injectable({
  providedIn: 'root'
})

export class NegociacoesService {

  negociacaoUrl: string;

  constructor(private http: MoneyHttp) {
    this.negociacaoUrl = `${environment.apiUrl}/negociaoes`;
  }
  listarTodas(): Promise<any> {
    return this.http.get<any>(this.negociacaoUrl)
      .toPromise()
      .then(response => response.content);
  }

  pesquisar(filtro: NegociacoesFiltro): Promise<any> {
    let params = new HttpParams({
      fromObject: {
        page: filtro.pagina.toString(),
        size: filtro.itensPorPagina.toString()
      }
    });
    if (filtro.nome) {
      params = params.append('nome', filtro.nome);
    }
    return this.http.get<any>(`${this.negociacaoUrl}`, { params })
      .toPromise()
      .then(response => {
        const negociacoes = response.content;
        const resultado = {
          negociacoes,
          total: response.totalElements
        };
        return resultado;
      })
  }

  excluir(id: number): Promise<void> {
    return this.http.delete(`${this.negociacaoUrl}/${id}`)
      .toPromise()
      .then(() => null);
  }

  adicionar(negociacao: Negociacao): Promise<Negociacao> {
    return this.http.post<Negociacao>(this.negociacaoUrl, negociacao)
      .toPromise();
  }

  atualizar(negociacao: Negociacao): Promise<Negociacao> {
    return this.http.put<Negociacao>(`${this.negociacaoUrl}/${negociacao.id}`, negociacao)
      .toPromise();
  }

  buscarPorCodigo(id: number): Promise<Negociacao> {
    return this.http.get<Negociacao>(`${this.negociacaoUrl}/${id}`)
      .toPromise();
  }
}
