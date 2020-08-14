import { Estagio } from './../models/estagio-negociacao.models';
import { HttpParams } from '@angular/common/http';
import { environment } from './../../../environments/environment';
import { MoneyHttp } from './../../usuarios/money-http';
import { Injectable } from '@angular/core';

export class EstagioFiltro {
  nome: string;
  pagina = 0;
  itensPorPagina = 5;
}

@Injectable({
  providedIn: 'root'
})
export class EstagioNegociacaoService {
  estagioUrl: string;

  constructor(private http: MoneyHttp) {
    this.estagioUrl = `${environment.apiUrl}/estagios`;
  }

  listarTodas(): Promise<any> {
    return this.http.get<any>(this.estagioUrl)
      .toPromise()
      .then(response => response.content);
  }

  pesquisar(filtro: EstagioFiltro): Promise<any> {
    let params = new HttpParams({
      fromObject: {
        page: filtro.pagina.toString(),
        size: filtro.itensPorPagina.toString()
      }
    });
    if (filtro.nome) {
      params = params.append('nome', filtro.nome);
    }
    return this.http.get<any>(`${this.estagioUrl}`, { params })
      .toPromise()
      .then(response => {
        const estagios = response.content;
        const resultado = {
          estagios,
          total: response.totalElements
        };
        return resultado;
      })
  }

  excluir(id: number): Promise<void> {
    return this.http.delete(`${this.estagioUrl}/${id}`)
      .toPromise()
      .then(() => null);
  }

  adicionar(estagio: Estagio): Promise<Estagio> {
    return this.http.post<Estagio>(this.estagioUrl, estagio)
      .toPromise();
  }

  atualizar(estagio: Estagio): Promise<Estagio> {
    return this.http.put<Estagio>(`${this.estagioUrl}/${estagio.id}`, estagio)
      .toPromise();
  }

  buscarPorCodigo(id: number): Promise<Estagio> {
    return this.http.get<Estagio>(`${this.estagioUrl}/${id}`)
      .toPromise();
  }
}
