export class Email {
    id: number;
    email: string;
    createdAt: Date;
    updatedAt: Date;
    createdByUser: string;
    modifiedByUser: string;
    constructor(id?: number,email?: string) {
        this.id = id;
        this.email = email;
    }
}