import { Cliente } from './../models/cliente.models';
import { HttpParams } from '@angular/common/http';
import { MoneyHttp } from './../../usuarios/money-http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

export class ClienteFiltro {
  nome: string;
  pagina = 0;
  itensPorPagina = 5;
}

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  clienteUrl: string;

  constructor(private http: MoneyHttp) {
    this.clienteUrl = `${environment.apiUrl}/clientes`;
  }

  detalhar(id: number): Promise<any> {
    return this.http.get<any>(`${this.clienteUrl}/${id}`)
        .toPromise()
        .then(response => response.content);
}

  listarTodas(): Promise<any> {
    return this.http.get<any>(this.clienteUrl)
      .toPromise()
      .then(response => response.content);
  }

  pesquisar(filtro: ClienteFiltro): Promise<any> {
    let params = new HttpParams({
      fromObject: {
        page: filtro.pagina.toString(),
        size: filtro.itensPorPagina.toString()
      }
    });
    if (filtro.nome) {
      params = params.append('nome', filtro.nome);
    }
    return this.http.get<any>(`${this.clienteUrl}`, { params })
      .toPromise()
      .then(response => {
        const clientes = response.content;
        const resultado = {
          clientes,
          total: response.totalElements
        };
        return resultado;
      })
  }

  excluir(id: number): Promise<void> {
    return this.http.delete(`${this.clienteUrl}/${id}`)
      .toPromise()
      .then(() => null);
  }

  adicionar(cliente: Cliente): Promise<Cliente> {
    return this.http.post<Cliente>(this.clienteUrl, cliente)
      .toPromise();
  }

  atualizar(cliente: Cliente): Promise<Cliente> {
    console.log(cliente);
    return this.http.put<Cliente>(`${this.clienteUrl}/${cliente.id}`, cliente)
      .toPromise();
  }

  buscarPorCodigo(id: number): Promise<Cliente> {
    return this.http.get<Cliente>(`${this.clienteUrl}/${id}`)
      .toPromise();
  }
}
