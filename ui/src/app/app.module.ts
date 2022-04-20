import { HttpClientModule } from '@angular/common/http';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ApiModule } from '@api/api.module';
import { Configuration } from '@api/configuration';
import { provideHttpErrorInterceptor } from '~/app/core/errors/http-error.interceptor';
import { SecurityModule } from '~/app/core/security/security.module';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    SecurityModule,
    AppRoutingModule,
    ApiModule.forRoot(() => {
      return new Configuration({
        basePath: '',
      });
    }),
  ],
  providers: [
    provideHttpErrorInterceptor(),
  ],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class AppModule {
}
