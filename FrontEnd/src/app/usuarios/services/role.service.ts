import { environment } from '../../../environments/environment';
import { MoneyHttp } from '../money-http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  rolesUrl: string;

  constructor(private http: MoneyHttp) {
    this.rolesUrl = `${environment.apiUrl}/roles`;
  }

  listarTodas(): Promise<any> {
    return this.http.get(this.rolesUrl)
      .toPromise();
  }
}
