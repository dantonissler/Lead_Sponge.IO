import { Produto } from './../../produtos/models/produto.models';

export class NegociacaoProduto{
    id: number;
    valor: number;
    desconto: number;
    quantidade: number;
    temDesconto: boolean;
    reincidencia: string;
    tipoDesconto: string;
    produto = new Produto();
    constructor(id?: number, valor?: number, desconto?: number, quantidade?: number,
                temDesconto?: boolean, reincidencia?: string, tipoDesconto?: string, produto = new Produto()) {
        this.id = id;
        this.valor = valor;
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.temDesconto = temDesconto;
        this.reincidencia = reincidencia;
        this.tipoDesconto = tipoDesconto;
        this.produto = produto;
    }
}
