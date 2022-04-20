import { Component } from '@angular/core';
import { changeDetection } from '~/change-detection.strategy';

@Component({
  template: `
    <app-layout>
      <router-outlet></router-outlet>
    </app-layout>
  `,
  changeDetection,
})
export class AdminView {
}
