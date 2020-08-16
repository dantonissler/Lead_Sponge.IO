import { Cliente } from './../../clientes/models/cliente.models';
import { Contato } from './../models/contato.models';
import { HttpParams } from '@angular/common/http';
import { MoneyHttp } from './../../usuarios/money-http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

export class ContatoFiltro {
  nome: string;
  pagina = 0;
  itensPorPagina = 5;
}

@Injectable({
  providedIn: 'root'
})
export class ContatoService {

  contatoUrl: string;
  clienteUrl: string;

  constructor(private http: MoneyHttp) {
    this.contatoUrl = `${environment.apiUrl}/contatos`;
    this.clienteUrl = `${environment.apiUrl}/clientes`;
  }
  listarTodas(): Promise<any> {
    return this.http.get<any>(this.contatoUrl)
      .toPromise()
      .then(response => response.content);
  }

  pesquisar(filtro: ContatoFiltro): Promise<any> {
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
      });
  }

  excluir(id: number): Promise<void> {
    return this.http.delete(`${this.contatoUrl}/${id}`)
      .toPromise()
      .then(() => null);
  }

  adicionar(contato: Contato): Promise<Contato> {
    return this.http.post<Contato>(this.contatoUrl, contato)
      .toPromise();
  }

  atualizar(contato: Contato): Promise<Contato> {
    return this.http.put<Contato>(`${this.contatoUrl}/${contato.id}`, contato)
      .toPromise();
  }

  buscarPorId(id: number): Promise<Contato> {
    return this.http.get<Contato>(`${this.contatoUrl}/${id}`)
      .toPromise();
  }
  listarCliente(): Promise<Cliente[]> {
    return this.http.get<Cliente[]>(`${this.clienteUrl}/listar`).toPromise();
  }
}
