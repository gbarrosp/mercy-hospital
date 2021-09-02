
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Patient } from 'src/app/models/patient.model';
import { Response } from 'src/app/models/response.model';
import { Constants } from 'src/app/util/constants';

@Injectable()
export class PatientService {

  constructor(private httpClient: HttpClient) { }
    
  getAllPatients() : Observable<Patient[]> {
    return this.httpClient.get<any>(`${Constants.BASE_URL}patient/all`, {headers: this.getHeaders()}).pipe(
      map((response: Response) => {
        const resp: Patient[] = response.data;
        return resp;
      }));
  }

  private getHeaders(): HttpHeaders {
    const headers = new HttpHeaders();
    return headers;
  }

}