export class Telefone {
    id: number;
    numero: string;
    tipo = 'COMERCIAL';

    constructor(id?: number,numero?: string,tipo?: string) {
        this.id = id;
        this.numero = numero;
        this.tipo = tipo;
    }
}