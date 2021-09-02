import { Injectable } from '@angular/core';

import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { tap } from 'rxjs/operators';



@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Adiciona a autorização (jwt Token)  no cabeçalho caso o usuário já tenha se autenticado
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));

    if (currentUser && currentUser.token) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${currentUser.token}`
        }
      });
    }

    return next.handle(request).pipe(tap((ev: HttpEvent<any>) => {
      let authRoute = request.url.endsWith('/login') || request.url.endsWith('/signup') || request.url.endsWith('/reset')
      if (ev instanceof HttpResponse) {
        if (authRoute && request.method === 'POST' && ev.body) {
          localStorage.setItem('currentUser', JSON.stringify(ev.body));
        }
      }
    }, (error: any) => {
      if (error instanceof HttpErrorResponse) {
        if (error.error.errors && error.error.errors.includes('token is expired')) {
          localStorage.clear()
          window.location.reload()
        }
        return throwError(error.error.errors);
      }
    }
    ));
  }
}
