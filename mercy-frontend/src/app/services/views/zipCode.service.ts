
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ZipCode } from 'src/app/models/zipCode.model';
import { Constants } from 'src/app/util/constants';

@Injectable()
export class ZipCodeService {

  constructor(private httpClient: HttpClient) { }
    
  getZipCodeInfo(zipCode: string) : Observable<ZipCode> {
    return this.httpClient.get<any>(`${Constants.VIA_CEP_API}${zipCode}/json`, {headers: this.getHeaders()}).pipe(
      map((response: any) => {
        if (response.erro) {
          return null
        }
        const resp: ZipCode = new ZipCode();
        resp.city = response.localidade
        resp.state = response.uf
        resp.streetName = response.logradouro
        resp.neighborhood = response.bairro
        return resp;
      }));
  }

  private getHeaders(): HttpHeaders {
    const headers = new HttpHeaders();
    return headers;
  }

}