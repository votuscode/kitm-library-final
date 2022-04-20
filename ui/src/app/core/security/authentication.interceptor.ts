import {
  HTTP_INTERCEPTORS,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable, Provider } from '@angular/core';
import { Observable, of } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { AuthenticationFacade } from '~/app/core/security/authentication.facade';

@Injectable()
class AuthenticationInterceptor implements HttpInterceptor {
  intercept = (req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> => {

    return of(this.authenticationFacade.getToken()).pipe(
      switchMap(token => {
        if (req.url === '/api/public/login') {
          return next.handle(req);
        }

        return next.handle(req.clone({
          headers: req.headers.set('Authorization', 'Bearer ' + token),
        }));
      }),
    );
  };

  constructor(readonly authenticationFacade: AuthenticationFacade) {
  }
}

export const provideAuthenticationInterceptor = (): Provider => ({
  provide: HTTP_INTERCEPTORS,
  useClass: AuthenticationInterceptor,
  multi: true,
});
