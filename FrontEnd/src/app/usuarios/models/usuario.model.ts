import { Role } from './role.models';
export class Usuario {
    id: number;
    username: string;
    password: string;
    confirmarPassword: string;
    nomeCompleto: string;
    email: string;
    enabled: boolean;
    roles: Role;
  }