import { AuthService } from './../../../usuarios/services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-configuracoes',
  templateUrl: './configuracoes.component.html',
  styleUrls: ['./configuracoes.component.css']
})
export class ConfiguracoesComponent implements OnInit {

  constructor(
    public auth: AuthService,
  ) {}

  ngOnInit(): void {
  }

}
