import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginView, RedirectView } from './components';

const routes: Routes = [
  { path: 'redirect', component: RedirectView },
  { path: 'login', component: LoginView },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class SecurityRoutingModule {
}
