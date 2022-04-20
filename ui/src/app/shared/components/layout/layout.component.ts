import { Component } from '@angular/core';
import { AuthenticationFacade } from '~/app/core/security/authentication.facade';
import { changeDetection } from '~/change-detection.strategy';

@Component({
  selector: 'app-layout',
  styles: [`
    .navbar-brand img {
      height: 1.5rem;
    }

    main.container {
      padding: 5rem 0;
    }
  `],
  template: `
    <nav class="navbar navbar-expand fixed-top navbar-light bg-light">
      <div class="container-fluid">
        <a class="navbar-brand" [routerLink]="home">
          <img src="/assets/library.svg" alt="Library">
        </a>
        <div class="collapse navbar-collapse">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" [routerLink]="home">Home</a>
            </li>
          </ul>
          <div class="d-flex">
            <ng-content select="[widget]"></ng-content>
          </div>
          <div class="d-flex ms-1">
            <div class="dropdown" *ngIf="authenticationFacade.user$ | async as user">
              <button class="dropdown-toggle btn btn-outline-secondary" href="#" id="navbarDropdown"
                      type="button"
                      data-bs-toggle="dropdown" aria-expanded="false">
                👤 {{ user.username }}
              </button>
              <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                <li>
                  <a class="dropdown-item" routerLink="/orders">Orders</a>
                </li>
                <li>
                  <a class="dropdown-item" routerLink="/wish-list">Wish list</a>
                </li>
                <li>
                  <hr class="dropdown-divider">
                </li>
                <!-- Logout button -->
                <button class="dropdown-item" (click)="authenticationFacade.logout()">
                  Logout
                </button>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </nav>
    <main class="container">
      <ng-content></ng-content>
    </main>
  `,
  changeDetection,
})
export class LayoutComponent {
  home = '/';

  items = [
    { label: 'Books', href: '/books' },
  ];

  constructor(readonly authenticationFacade: AuthenticationFacade) {
  }
}
