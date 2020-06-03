import { Role } from './role.models';
export class Usuario {
    id: number;
    login: string;
    nomeCompleto: string;
    email: string;
    senha: string;
    roles: Role;
  }