import { Usuario } from '../models/usuario.model';
import { environment } from '../../../environments/environment';
import { MoneyHttp } from '../money-http';

import { HttpParams, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';


export class UsuarioFiltro {
    nomeCompleto: string;
    username: string;
    email: string;
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
        } else if (filtro.username) {
            params = params.append('username', filtro.username);
        }
        else if (filtro.email) {
            params = params.append('email', filtro.email);
        }
        return this.http.get<any>(`${this.usuariosUrl}?resumo`, { params })
            .toPromise()
            .then(response => {
                const usuarios = response.content;
                const resultado = {
                    usuarios,
                    total: response.totalElements
                };
                return resultado;
            });
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

    urlUploadAnexo(): string {
        return `${this.usuariosUrl}/anexo`;
    }

    buscarPeloNome(username: string): Promise<Usuario>{
        return this.http.get<Usuario>(`${this.usuariosUrl}/username/${username}`)
            .toPromise();
    }

    atualizarImg(id: number, anexo: string): Promise<void> {
        const headers = new HttpHeaders()
            .append('Content-Type', 'application/json');
        return this.http.put(`${this.usuariosUrl}/${id}/img`, anexo, { headers })
            .toPromise()
            .then(() => null);
    }

    removerImg(id: number): Promise<void> {
        const headers = new HttpHeaders()
            .append('Content-Type', 'application/json');
        return this.http.put(`${this.usuariosUrl}/${id}/removerImg`, { headers })
            .toPromise()
            .then(() => null);
    }
}
