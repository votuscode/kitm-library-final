import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '@api/api/authentication.service';
import { UserDto } from '@api/model/userDto';
import { BehaviorSubject, of } from 'rxjs';
import { catchError, map, switchMap, tap } from 'rxjs/operators';
import { ToastService } from '~/app/toast.service';
import { environment } from '~/environments/environment';

@Injectable({ providedIn: 'root' })
export class AuthenticationFacade {
  readonly user$ = new BehaviorSubject<UserDto | undefined>(undefined);

  constructor(readonly authenticationService: AuthenticationService, readonly toastService: ToastService, readonly router: Router) {
  }

  setToken = (token: string) => {
    localStorage.setItem('token', token);

    this.authenticationService.authenticated().pipe(
      switchMap(() => {
        return of(this.router.navigateByUrl('/'));
      }),
    ).subscribe();
  };

  getToken = () => {
    return localStorage.getItem('token');
  };

  login = (username: string, password: string) => {
    this.authenticationService.login({ username, password }).pipe(
      switchMap(({ token }) => {
        this.setToken(token);
        this.toastService.success('Login successful.');
        return of(this.router.navigate(['/']));
      }),
    ).subscribe();
  };

  authenticated = () => {
    return this.authenticationService.authenticated().pipe(
      tap(authenticatedDto => {
        this.user$.next(authenticatedDto.user);
      }),
      map(() => true),
      catchError(() => {
        this.logout();
        return of(false);
      }),
    );
  };

  logout = () => {
    localStorage.removeItem('token');
    window.location.assign(environment.login);
  };
}
