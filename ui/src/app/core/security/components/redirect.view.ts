import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthenticationFacade } from '~/app/core/security/authentication.facade';
import { changeDetection } from '~/change-detection.strategy';

@Component({
  template: ``,
  changeDetection,
})
export class RedirectView {

  constructor(readonly authenticationFacade: AuthenticationFacade, readonly route: ActivatedRoute) {
    const { token } = route.snapshot.queryParams;
    authenticationFacade.setToken(token);
  }
}
