import { Usuario } from '../models/usuario.model';
import { environment } from '../../../environments/environment';
import { MoneyHttp } from '../money-http';

import { HttpParams, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';


export class UsuarioFiltro {
    nomeCompleto: string;
    pagina = 0;
    itensPorPagina = 5;
}

@Injectable()
export class UsuarioService {

  usuariosUrl: string;

  constructor(private http: MoneyHttp) {
    this.usuariosUrl = `${environment.apiUrl}/usuarios`;
  }

  listarTodas(): Promise<any> {
    return this.http.get<any>(this.usuariosUrl)
      .toPromise()
      .then(response => response.content);
  }

  pesquisar(filtro: UsuarioFiltro): Promise<any> {
    let params = new HttpParams({
      fromObject: {
        page: filtro.pagina.toString(),
        size: filtro.itensPorPagina.toString()
      }
    });
    if (filtro.nomeCompleto) {
      params = params.append('nomeCompleto', filtro.nomeCompleto);
    }
    return this.http.get<any>(`${this.usuariosUrl}`, { params })
      .toPromise()
      .then(response => {
        const usuarios = response.content;
        const resultado = {
          usuarios,
          total: response.totalElements
        };
        return resultado;
      })
  }

  excluir(id: number): Promise<void> {
    return this.http.delete(`${this.usuariosUrl}/${id}`)
      .toPromise()
      .then(() => null);
  }

  mudarStatus(id: number, ativo: boolean): Promise<void> {
    const headers = new HttpHeaders()
        .append('Content-Type', 'application/json');
    return this.http.put(`${this.usuariosUrl}/${id}/ativo`, ativo, { headers })
      .toPromise()
      .then(() => null);
  }

  adicionar(usuario: Usuario): Promise<Usuario> {
    return this.http.post<Usuario>(this.usuariosUrl, usuario)
      .toPromise();
  }

  atualizar(usuario: Usuario): Promise<Usuario> {
    return this.http.put<Usuario>(`${this.usuariosUrl}/${usuario.id}`, usuario)
      .toPromise()
      .then(response => {
        const usuarioAlterado = response;
        return usuarioAlterado;
      });
  }

  buscarPorCodigo(id: number): Promise<Usuario> {
    return this.http.get<Usuario>(`${this.usuariosUrl}/${id}`)
      .toPromise();
  }
}