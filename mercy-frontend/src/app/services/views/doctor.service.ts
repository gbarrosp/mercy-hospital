
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Doctor } from 'src/app/models/doctor.model';
import { Response } from 'src/app/models/response.model';
import { Constants } from 'src/app/util/constants';

@Injectable()
export class DoctorService {

  constructor(private httpClient: HttpClient) { }
    
  getAllDoctors() : Observable<Doctor[]> {
    return this.httpClient.get<any>(`${Constants.BASE_URL}doctor/all`, {headers: this.getHeaders()}).pipe(
      map((response: Response) => {
        const resp: Doctor[] = response.data;
        return resp;
      }));
  }

  newDoctor(doctor: Doctor) : Observable<Doctor> {
    return this.httpClient.post<any>(`${Constants.BASE_URL}doctor/new`, doctor, {headers: this.getHeaders()}).pipe(
      map((response: Response) => {
        const resp: Doctor = response.data;
        return resp
      }));
  }

  private getHeaders(): HttpHeaders {
    const headers = new HttpHeaders();
    return headers;
  }

}