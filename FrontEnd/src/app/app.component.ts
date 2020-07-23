import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  
  constructor(private router: Router) {}

  exibindoLogin() {
    if(this.router.url === '/login'){
      return false;
    }
    if (this.router.url === '/pagina-nao-encontrada'){
      return false;
    }
    return true;
  }
}
