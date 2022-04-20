import { Component } from '@angular/core';
import { changeDetection } from '~/change-detection.strategy';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  changeDetection,
})
export class AppComponent {

}
