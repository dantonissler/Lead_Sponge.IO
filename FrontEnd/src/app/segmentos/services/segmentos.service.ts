import { HttpParams } from '@angular/common/http';
import { environment } from './../../../environments/environment';
import { MoneyHttp } from './../../usuarios/money-http';
import { Injectable } from '@angular/core';
import { Segmento } from '../models/segmento.models';

export class SegmentoFiltro {
  nome: string;
  pagina = 0;
  itensPorPagina = 5;
}

@Injectable({
  providedIn: 'root'
})
export class SegmentosService {

  segmentoUrl: string;

  constructor(private http: MoneyHttp) {
    this.segmentoUrl = `${environment.apiUrl}/segmentos`;
  }
  listarTodas(): Promise<any> {
    return this.http.get<any>(this.segmentoUrl)
      .toPromise()
      .then(response => response.content);
  }

  pesquisar(filtro: SegmentoFiltro): Promise<any> {
    let params = new HttpParams({
      fromObject: {
        page: filtro.pagina.toString(),
        size: filtro.itensPorPagina.toString()
      }
    });
    if (filtro.nome) {
      params = params.append('nome', filtro.nome);
    }
    return this.http.get<any>(`${this.segmentoUrl}`, { params })
      .toPromise()
      .then(response => {
        const segmentos = response.content;
        const resultado = {
          segmentos,
          total: response.totalElements
        };
        return resultado;
      })
  }

  excluir(id: number): Promise<void> {
    return this.http.delete(`${this.segmentoUrl}/${id}`)
      .toPromise()
      .then(() => null);
  }

  adicionar(segmento: Segmento): Promise<Segmento> {
    return this.http.post<Segmento>(this.segmentoUrl, segmento)
      .toPromise();
  }

  atualizar(segmento: Segmento): Promise<Segmento> {
    return this.http.put<Segmento>(`${this.segmentoUrl}/${segmento.id}`, segmento)
      .toPromise();
  }

  buscarPorCodigo(id: number): Promise<Segmento> {
    return this.http.get<Segmento>(`${this.segmentoUrl}/${id}`)
      .toPromise();
  }
}
