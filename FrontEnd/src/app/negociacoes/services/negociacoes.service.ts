import { Perda } from './../../motivoPerda/models/motivo-perda.models';
import { Estagio } from './../../estagioNegociacao/models/estagio-negociacao.models';
import { Negociacao } from './../models/negociacao.models';
import { HttpParams, HttpHeaders } from '@angular/common/http';
import { MoneyHttp } from './../../usuarios/money-http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import * as moment from 'moment';

export class NegociacoesFiltro {
    nome: string;
    pagina = 0;
    itensPorPagina = 5;
}

@Injectable({
    providedIn: 'root'
})

export class NegociacoesService {

    negociacaoUrl: string;

    constructor(private http: MoneyHttp) {
        this.negociacaoUrl = `${environment.apiUrl}/negociacoes`;
    }

    listarTodas(): Promise<any> {
        return this.http.get<any>(this.negociacaoUrl)
            .toPromise()
            .then(response => response.content);
    }

    pesquisar(filtro: NegociacoesFiltro): Promise<any> {
        let params = new HttpParams({
            fromObject: {
                page: filtro.pagina.toString(),
                size: filtro.itensPorPagina.toString()
            }
        });
        if (filtro.nome) {
            params = params.append('nome', filtro.nome);
        }
        return this.http.get<any>(`${this.negociacaoUrl}`, { params })
            .toPromise()
            .then(response => {
                const negociacoes = response.content;
                const resultado = {
                    negociacoes,
                    total: response.totalElements
                };
                return resultado;
            });
    }

    excluir(id: number): Promise<void> {
        return this.http.delete(`${this.negociacaoUrl}/${id}`)
            .toPromise()
            .then(() => null);
    }

    adicionar(negociacao: Negociacao): Promise<Negociacao> {
        return this.http.post<Negociacao>(this.negociacaoUrl, negociacao)
            .toPromise();
    }

    atualizar(negociacao: Negociacao): Promise<Negociacao> {
        return this.http.put<Negociacao>(`${this.negociacaoUrl}/${negociacao.id}`, negociacao)
            .toPromise();
    }

    buscarPorCodigo(id: number): Promise<Negociacao> {
        return this.http.get<Negociacao>(`${this.negociacaoUrl}/${id}`)
            .toPromise()
            .then(response => {
                const tarefaAlterada = response;
                this.converterStringsParaDatas([tarefaAlterada]);
                return tarefaAlterada;
            });
    }

    private converterStringsParaDatas(negociacaos: Negociacao[]) {
        for (const negociacao of negociacaos) {
            negociacao.createdAt = moment(negociacao.createdAt,
                'YYYY-MM-DD').toDate();
        }
    }

    mudarAvaliacao(id: number, avaliacao: number): Promise<void> {
        const headers = new HttpHeaders()
            .append('Content-Type', 'application/json; charset=utf-8');
        return this.http.put(`${this.negociacaoUrl}/${id}/avaliacao`, avaliacao, { headers })
            .toPromise()
            .then(() => null);
    }

    mudarEstatus(id: number, estatus: string): Promise<void> {
        const headers = new HttpHeaders()
            .append('Content-Type', 'application/json; charset=utf-8');
        return this.http.put(`${this.negociacaoUrl}/${id}/estatus`, `"${estatus}"`, { headers })
            .toPromise()
            .then(() => null);
    }

    mudarData(id: number, dataFim: any): Promise<void> {
        const headers = new HttpHeaders()
            .append('Content-Type', 'application/json; charset=utf-8');
        return this.http.put(`${this.negociacaoUrl}/${id}/dataFim`, dataFim, { headers })
            .toPromise()
            .then(() => null);
    }

    mudarEstagio(id: number, estagio: Estagio): Promise<void> {
        const headers = new HttpHeaders()
            .append('Content-Type', 'application/json; charset=utf-8');
        return this.http.put(`${this.negociacaoUrl}/${id}/estagio`, estagio, { headers })
            .toPromise()
            .then(() => null);
    }

    atribuirMotivoPerda(id: number, perda: Perda): Promise<void> {
        const headers = new HttpHeaders()
            .append('Content-Type', 'application/json; charset=utf-8');
        return this.http.put(`${this.negociacaoUrl}/${id}/perda`, perda, { headers })
            .toPromise()
            .then(() => null);
    }


}
