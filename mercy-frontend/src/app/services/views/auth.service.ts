
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Response } from 'src/app/models/response.model';
import { Constants } from 'src/app/util/constants';

@Injectable()
export class AuthService {

  constructor(
    private httpClient: HttpClient
    ) { }

  login(username: string, password: string): Observable<any> {
    return this.httpClient.post<Response>(`${Constants.BASE_URL}login`,
    JSON.stringify({ username: username, password: password }), { headers: this.getHeaders() })
    .pipe(
      map((response: any) => {
        const user = response;
        return user;
      }));
  }

  private getHeaders(): HttpHeaders {
    const headers = new HttpHeaders({'content-type': 'application/json', accept: 'application/json'});
    return headers;
  }

}
