import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { MoneyHttp } from 'src/app/usuarios/money-http';
import { NegociacaoProduto } from '../models/negociacao-produto-models';

@Injectable({
    providedIn: 'root'
})

export class NegociacaoProdutoService {

    negociacaoUrl: string;

    constructor(private http: MoneyHttp) {
        this.negociacaoUrl = `${environment.apiUrl}/negociacaoProduto`;
    }

    adicionarVenda(negociacaoProduto: NegociacaoProduto): Promise<NegociacaoProduto> {
        return this.http.post<NegociacaoProduto>(this.negociacaoUrl, negociacaoProduto)
            .toPromise();
    }

    atualizar(negociacaoProduto: NegociacaoProduto): Promise<NegociacaoProduto> {
        return this.http.put<NegociacaoProduto>(`${this.negociacaoUrl}/${negociacaoProduto.id}`, negociacaoProduto)
            .toPromise();
    }

    excluir(id: number): Promise<void> {
        return this.http.delete(`${this.negociacaoUrl}/${id}`)
            .toPromise()
            .then(() => null);
    }
}
