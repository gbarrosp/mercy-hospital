import { Injectable } from '@angular/core';

import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Constants } from 'src/app/util/constants';



@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Adiciona a autorização (jwt Token)  no cabeçalho caso o usuário já tenha se autenticado
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));

    if (currentUser && currentUser.token && !request.url.startsWith(Constants.VIA_CEP_API)) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${currentUser.token}`
        }
      });
    }

    return next.handle(request).pipe(tap((ev: HttpEvent<any>) => {
      let authRoute = request.url.endsWith('/login') || request.url.endsWith('/sign-up')
      if (ev instanceof HttpResponse) {
        if (authRoute && request.method === 'POST' && ev.body) {
          localStorage.setItem('currentUser', JSON.stringify(ev.body.data));
        }
      }
    }, (error: any) => {
      if (error instanceof HttpErrorResponse) {
        if (error.error && error.error.error.includes('Unauthorized')) {
          localStorage.clear()
          window.location.reload()
        }
        return throwError(error.error.errors);
      }
    }
    ));
  }
}
