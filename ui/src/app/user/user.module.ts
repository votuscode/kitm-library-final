import { CommonModule } from '@angular/common';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SharedModule } from '~/app/shared/shared.module';
// feature
import { UserRoutingModule } from './user-routing.module';
import { components } from './components';

@NgModule({
  imports: [CommonModule, RouterModule, UserRoutingModule, SharedModule],
  declarations: [...components],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class UserModule {
}
