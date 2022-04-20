import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
// feature
import { AdminRoutingModule } from './admin-routing.module';
import { AdminFacade } from './admin.facade';
import { components } from './components';

@NgModule({
  imports: [CommonModule, ReactiveFormsModule, AdminRoutingModule],
  declarations: [...components],
  providers: [AdminFacade]
})
export class AdminModule {
}
