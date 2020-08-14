import { HttpParams } from '@angular/common/http';

import { MoneyHttp } from './../../usuarios/money-http';
import { environment } from './../../../environments/environment';
import { Injectable } from '@angular/core';
import { Fonte } from '../models/fonte.models';

export class FonteFiltro {
  nome: string;
  pagina = 0;
  itensPorPagina = 5;
}

@Injectable({
  providedIn: 'root'
})
export class FontesService {

  fonteUrl: string;

  constructor(private http: MoneyHttp) {
    this.fonteUrl = `${environment.apiUrl}/fontes`;
  }

  listarTodas(): Promise<any> {
    return this.http.get<any>(this.fonteUrl)
      .toPromise()
      .then(response => response.content);
  }

  pesquisar(filtro: FonteFiltro): Promise<any> {
    let params = new HttpParams({
      fromObject: {
        page: filtro.pagina.toString(),
        size: filtro.itensPorPagina.toString()
      }
    });
    if (filtro.nome) {
      params = params.append('nome', filtro.nome);
    }
    return this.http.get<any>(`${this.fonteUrl}`, { params })
      .toPromise()
      .then(response => {
        const fontes = response.content;
        const resultado = {
         fontes,
          total: response.totalElements
        };
        return resultado;
      })
  }

  excluir(id: number): Promise<void> {
    return this.http.delete(`${this.fonteUrl}/${id}`)
      .toPromise()
      .then(() => null);
  }

  adicionar(fonte: Fonte): Promise<Fonte> {
    return this.http.post<Fonte>(this.fonteUrl, fonte)
      .toPromise();
  }

  atualizar(fonte: Fonte): Promise<Fonte> {
    return this.http.put<Fonte>(`${this.fonteUrl}/${fonte.id}`, fonte)
      .toPromise();
  }

  buscarPorCodigo(id: number): Promise<Fonte> {
    return this.http.get<Fonte>(`${this.fonteUrl}/${id}`)
      .toPromise();
  }
}
