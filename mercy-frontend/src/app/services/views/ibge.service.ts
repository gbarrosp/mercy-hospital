
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ZipCode } from 'src/app/models/zipCode.model';
import { Constants } from 'src/app/util/constants';

@Injectable()
export class IBGEService {

  constructor(private httpClient: HttpClient) { }
    
  getAllStates() : Observable<any> {
    return this.httpClient.get<any>(`${Constants.IBGE_API}`, {headers: this.getHeaders()}).pipe(
      map((response: any) => {
        return response.map(state => state.sigla).sort((a, b) => a.localeCompare(b));
      }));
  }

  getAllStateCities(state: string) : Observable<any> {
    return this.httpClient.get<any>(`${Constants.IBGE_API}/${state}/municipios`, {headers: this.getHeaders()}).pipe(
      map((response: any) => {
        return response.map(state => state.sigla).sort((a, b) => a.localeCompare(b));
      }));
  }

  private getHeaders(): HttpHeaders {
    const headers = new HttpHeaders();
    return headers;
  }

}