import { UsuarioService } from './../../../usuarios/services/usuario.service';
import { LogoutService } from '../../../usuarios/services/logout.service';
import { ErrorHandlerService } from '../../error-handler.service';
import { AuthService } from '../../../usuarios/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api/menuitem';

@Component({
    selector: 'app-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

    visibleSidebar1;
    items: MenuItem[];
    users: MenuItem[];
    url;

    constructor(
        public auth: AuthService,
        public usuarioService: UsuarioService,
        private logoutService: LogoutService,
        private errorHandler: ErrorHandlerService,
        private router: Router
    ) { }

    ngOnInit(): void {
        this.carregarMenu();
        this.carregarUsuarios();
        this.carregarUsuario(this.auth.jwtPayload?.user_name);
    }

    carregarUsuario(username: string) {
        this.usuarioService.buscarPeloNome(username)
            .then(usuario => {
                this.url = '///leadspongeuserimagens.s3.us-east-2.amazonaws.com/' + usuario.anexo;
            })
            .catch(erro => this.errorHandler.handle(erro));
    }

    carregarMenu() {
        this.items = [
            {
                label: 'Menu',
                icon: 'fa fa-bars',
                command: (onclick) => { this.visibleSidebar1 = true; }
            },
        ];
    }

    carregarUsuarios() {
        this.users = [
            {
                label: 'Help',
                icon: 'pi pi-fw pi-question',
                items: [
                    {
                        label: 'Contents'
                    },
                    {
                        label: 'Search',
                        icon: 'pi pi-fw pi-search',
                        items: [
                            {
                                label: 'Text',
                                items: [
                                    {
                                        label: 'Workspace'
                                    }
                                ]
                            },
                            {
                                label: 'File'
                            }
                        ]
                    }
                ]
            },
            {
                label: 'Configurações',
                icon: 'pi pi-fw pi-cog',
                routerLink: '/configuracoes'

            },
            { separator: true },
            {
                label: 'Sair',
                icon: 'pi pi-sign-out',
                command: (onclick) => { this.logout(); }
            }];
    }

    logout() {
        this.logoutService.logout()
            .then(() => {
                this.router.navigate(['/login']);
            })
            .catch(erro => this.errorHandler.handle(erro));
    }
}
