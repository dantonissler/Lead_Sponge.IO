import { Campanha } from './../models/campanha.models';
import { HttpParams } from '@angular/common/http';
import { environment } from './../../../environments/environment';
import { MoneyHttp } from './../../usuarios/money-http';
import { Injectable } from '@angular/core';

export class CampanhaFiltro {
  nome: string;
  pagina = 0;
  itensPorPagina = 5;
}

@Injectable({
  providedIn: 'root'
})
export class CampanhasService {

  campanhaUrl: string;

  constructor(private http: MoneyHttp) {
    this.campanhaUrl = `${environment.apiUrl}/campanhas`;
  }
  listarTodas(): Promise<any> {
    return this.http.get<any>(this.campanhaUrl)
      .toPromise()
      .then(response => response.content);
  }

  pesquisar(filtro: CampanhaFiltro): Promise<any> {
    let params = new HttpParams({
      fromObject: {
        page: filtro.pagina.toString(),
        size: filtro.itensPorPagina.toString()
      }
    });
    if (filtro.nome) {
      params = params.append('nome', filtro.nome);
    }
    return this.http.get<any>(`${this.campanhaUrl}`, { params })
      .toPromise()
      .then(response => {
        const campanhas = response.content;
        const resultado = {
         campanhas,
          total: response.totalElements
        };
        return resultado;
      })
  }

  excluir(id: number): Promise<void> {
    return this.http.delete(`${this.campanhaUrl}/${id}`)
      .toPromise()
      .then(() => null);
  }

  adicionar(campanha: Campanha): Promise<Campanha> {
    return this.http.post<Campanha>(this.campanhaUrl, campanha)
      .toPromise();
  }

  atualizar(campanha: Campanha): Promise<Campanha> {
    return this.http.put<Campanha>(`${this.campanhaUrl}/${campanha.id}`, campanha)
      .toPromise();
  }

  buscarPorCodigo(id: number): Promise<Campanha> {
    return this.http.get<Campanha>(`${this.campanhaUrl}/${id}`)
      .toPromise();
  }
}
