import { Role } from './role.models';
export class Usuario {
    id: number;
    username: string;
    password: string;
    confirmarPassword: string;
    nomeCompleto: string;
    email: string;
    enabled: boolean;
    foto: string;
    urlFoto: string;
    roles: Role;
}

export class UsuarioDTO {
    username: string;
    nomeCompleto: string;
    email: string;
    roles: Role;
}
