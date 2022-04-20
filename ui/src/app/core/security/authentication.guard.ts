import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { AuthenticationFacade } from '~/app/core/security/authentication.facade';

@Injectable({ providedIn: 'root' })
export class AuthenticationGuard implements CanActivate {
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.authenticationFacade.authenticated();
  }

  constructor(readonly authenticationFacade: AuthenticationFacade, readonly router: Router) {
  }
}
