import { HttpParams } from '@angular/common/http';
import { MoneyHttp } from './../../../usuarios/money-http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

export class LixeiraFiltro {
  nome: string;
  pagina = 0; 
  itensPorPagina = 5;
}

@Injectable({
  providedIn: 'root'
})
export class LixeiraService {
  contatoUrl: string;

  constructor(private http: MoneyHttp) {
    this.contatoUrl = `${environment.apiUrl}/lixeira`;
  }

  pesquisar(filtro: LixeiraFiltro): Promise<any> {
    let params = new HttpParams({
      fromObject: {
        page: filtro.pagina.toString(),
        size: filtro.itensPorPagina.toString()
      }
    });
    if (filtro.nome) {
      params = params.append('nome', filtro.nome);
    }
    return this.http.get<any>(`${this.contatoUrl}`, { params })
      .toPromise()
      .then(response => {
        const contatos = response.content;
        const resultado = {
          contatos,
          total: response.totalElements
        };
        return resultado;
      })
  }
}
