import { Component } from '@angular/core';
import { AuthenticationFacade } from '~/app/core/security/authentication.facade';
import { changeDetection } from '~/change-detection.strategy';

@Component({
  template: `
    <style>
      .login-form {
        width: 22rem;
        margin: 1rem auto 0;
      }

      @media (max-width: 500px) {
        .login-form {
          min-width: calc(100vw - 1rem);
        }
      }
    </style>
    <div class="container">
      <div class="card login-form">
        <img src="/assets/kitm-logo.png" class="card-img-top" alt="KITM">
        <div class="card-body">
          <h5 class="card-title">KITM Library</h5>
          <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Provident,
            veritatis.</p>
          <div class="alert alert-danger" role="alert" *ngIf="error">
            Could not login, please check your credentials and try again.
          </div>
          <div class="alert alert-success" role="alert" *ngIf="success">
            Successfully logged out.
          </div>
          <form>
            <div class="form-group mb-3">
              <label for="username">Username</label>
              <input name="username" type="text" class="form-control" id="username" placeholder="Username"
                     required #username>
            </div>
            <div class="form-group mb-3">
              <label for="password">Password</label>
              <input name="password" type="password" class="form-control" id="password"
                     placeholder="Password" required #password>
            </div>
            <button type="button" class="btn btn-primary"
                    (click)="authenticationFacade.login(username.value, password.value)">
              Login
            </button>
          </form>
        </div>
      </div>
    </div>
  `,
  changeDetection,
})
export class LoginView {

  success?: string;
  error?: string;

  constructor(readonly authenticationFacade: AuthenticationFacade) {
  }
}
