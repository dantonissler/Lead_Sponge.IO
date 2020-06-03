import { environment } from './../../environments/environment';
import { MoneyHttp } from './../seguranca/money-http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  lancamentosUrl: string;

  constructor(private http: MoneyHttp) {
    this.lancamentosUrl = `${environment.apiUrl}/lancamentos`;
  }
}
