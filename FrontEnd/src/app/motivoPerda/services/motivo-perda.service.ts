import { Perda } from './../models/motivo-perda.models';
import { HttpParams } from '@angular/common/http';
import { environment } from './../../../environments/environment';
import { MoneyHttp } from './../../usuarios/money-http';
import { Injectable } from '@angular/core';

export class MotivoPerdaFiltro {
  nome: string;
  pagina = 0;
  itensPorPagina = 5;
}

@Injectable({
  providedIn: 'root'
})
export class MotivoPerdaService {


  motivoPerdaUrl: string;

  constructor(private http: MoneyHttp) {
    this.motivoPerdaUrl = `${environment.apiUrl}/motivoperda`;
  }

  listarTodas(): Promise<any> {
    return this.http.get<any>(this.motivoPerdaUrl)
      .toPromise()
      .then(response => response.content);
  }

  pesquisar(filtro: MotivoPerdaFiltro): Promise<any> {
    let params = new HttpParams({
      fromObject: {
        page: filtro.pagina.toString(),
        size: filtro.itensPorPagina.toString()
      }
    });
    if (filtro.nome) {
      params = params.append('nome', filtro.nome);
    }
    return this.http.get<any>(`${this.motivoPerdaUrl}`, { params })
      .toPromise()
      .then(response => {
        const motivoPerdas = response.content;
        const resultado = {
          motivoPerdas,
          total: response.totalElements
        };
        return resultado;
      })
  }

  excluir(id: number): Promise<void> {
    return this.http.delete(`${this.motivoPerdaUrl}/${id}`)
      .toPromise()
      .then(() => null);
  }

  adicionar(motivoPerda: Perda): Promise<Perda> {
    return this.http.post<Perda>(this.motivoPerdaUrl, motivoPerda)
      .toPromise();
  }

  atualizar(motivoPerda: Perda): Promise<Perda> {
    return this.http.put<Perda>(`${this.motivoPerdaUrl}/${motivoPerda.id}`, motivoPerda)
      .toPromise();
  }

  buscarPorCodigo(id: number): Promise<Perda> {
    return this.http.get<Perda>(`${this.motivoPerdaUrl}/${id}`)
      .toPromise();
  }
}
