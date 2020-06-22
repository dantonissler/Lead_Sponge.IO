import { LogoutService } from '../../../usuarios/services/logout.service';
import { ErrorHandlerService } from '../../error-handler.service';
import { AuthService } from '../../../usuarios/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api/menuitem';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  visibleSidebar1;

  items: MenuItem[];
  users: MenuItem[];

  constructor(
    public auth: AuthService,
    private logoutService: LogoutService,
    private errorHandler: ErrorHandlerService,
    private router: Router
  ) {}
  
  ngOnInit(): void {
    this.users =[
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
                ]}
            ]
        },
        {
            label: 'Configurações',
            icon: 'pi pi-fw pi-cog',
            routerLink:'/configuracoes'
            
        },
        {separator:true},
        {
            label: 'Sair',
            icon: 'pi pi-sign-out',
            command: (onclick)=> {this.logout()}
        }];
    this.items = [
        {
            label: 'Menu',
            icon: 'fa fa-bars',
            command: (onclick)=> {this.visibleSidebar1 = true}
        },
    ];}
  
  
  logout() {
    this.logoutService.logout()
      .then(() => {
        this.router.navigate(['/login']);
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

}
