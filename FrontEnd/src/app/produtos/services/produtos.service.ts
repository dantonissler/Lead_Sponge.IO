import { Produto } from './../models/produto.models';
import { HttpParams, HttpHeaders } from '@angular/common/http';
import { MoneyHttp } from './../../usuarios/money-http';
import { environment } from './../../../environments/environment';
import { Injectable } from '@angular/core';

export class ProdutoFiltro {
  nome: string;
  descricao: string;
  pagina = 0;
  itensPorPagina = 5;
}

@Injectable({
  providedIn: 'root'
})
export class ProdutosService {

  produtoUrl: string;

  constructor(private http: MoneyHttp) {
    this.produtoUrl = `${environment.apiUrl}/produtos`;
  }

  listarTodas(): Promise<any> {
    return this.http.get<any>(this.produtoUrl)
      .toPromise()
      .then(response => response.content);
  }

  pesquisar(filtro: ProdutoFiltro): Promise<any> {
    let params = new HttpParams({
      fromObject: {
        page: filtro.pagina.toString(),
        size: filtro.itensPorPagina.toString()
      }
    });
    if (filtro.nome) {
      params = params.append('nome', filtro.nome);
    }
    if (filtro.descricao) {
      params = params.append('descricao', filtro.descricao);
    }
    return this.http.get<any>(`${this.produtoUrl}`, { params })
      .toPromise()
      .then(response => {
        const produtos = response.content;
        const resultado = {
          produtos,
          total: response.totalElements
        };
        return resultado;
      })
  }

  excluir(id: number): Promise<void> {
    return this.http.delete(`${this.produtoUrl}/${id}`)
      .toPromise()
      .then(() => null);
  }

  adicionar(produto: Produto): Promise<Produto> {
    return this.http.post<Produto>(this.produtoUrl, produto)
      .toPromise();
  }

  atualizar(produto: Produto): Promise<Produto> {
    console.log(produto);
    return this.http.put<Produto>(`${this.produtoUrl}/${produto.id}`, produto)
      .toPromise();
  }

  buscarPorCodigo(id: number): Promise<Produto> {
    return this.http.get<Produto>(`${this.produtoUrl}/${id}`)
      .toPromise();
  }

  mudarVisibilidade(id: number, visibilidade: boolean): Promise<void> {
    console.log(visibilidade);
    const headers = new HttpHeaders()
        .append('Content-Type', 'application/json');
    return this.http.put(`${this.produtoUrl}/${id}/vasivel`, visibilidade, { headers })
      .toPromise()
      .then(() => null);
  }
}
