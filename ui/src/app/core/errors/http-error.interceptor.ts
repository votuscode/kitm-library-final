import {
  HTTP_INTERCEPTORS,
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable, Optional, Provider } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ToastService } from '~/app/toast.service';

interface ApiError<T = unknown> {
  error: {
    message: string;
    details: T;
  };
}

const isApiError = (value?: any): value is ApiError => {
  return value?.error?.message && value?.error?.details;
};

const extractErrorMessage = (error: HttpErrorResponse): [message: string, header: string] => {
  if (error.error instanceof ErrorEvent) {
    return [error.error.message, 'Client-side error'];
  }

  if (isApiError(error)) {
    return [error.error.details, error.error.message];
  }

  return [`Error Code: ${error.status}
Message: ${error.message}`, 'Server-side error'];
};

@Injectable()
class HttpErrorInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      catchError((err: HttpErrorResponse) => {
        if (err.status === 403) {
          void this.router.navigateByUrl('/login');
          return throwError(err);
        }

        if (this.toastService) {
          console.log(extractErrorMessage(err));
          this.toastService.error(...extractErrorMessage(err));
        }
        return throwError(err);
      }),
    );
  }

  constructor(@Optional() readonly toastService: ToastService, readonly router: Router) {
  }
}

export const provideHttpErrorInterceptor = (): Provider => ({
  provide: HTTP_INTERCEPTORS,
  useClass: HttpErrorInterceptor,
  multi: true,
});
