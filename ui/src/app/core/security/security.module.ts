import { CommonModule } from '@angular/common';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { provideAuthenticationInterceptor } from '~/app/core/security/authentication.interceptor';
import { SecurityRoutingModule } from '~/app/core/security/security-routing.module';
import { components } from './components';

@NgModule({
  declarations: [
    ...components,
  ],
  imports: [
    CommonModule,
    SecurityRoutingModule,
  ],
  providers: [
    provideAuthenticationInterceptor(),
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class SecurityModule {
}
