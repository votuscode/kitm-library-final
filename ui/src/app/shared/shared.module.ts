import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
// feature
import { components } from './components';
import { pipes } from './pipes';

@NgModule({
    imports: [
      CommonModule,
      RouterModule,
    ],
    declarations: [...components, ...pipes],
    exports: [...components, ...pipes],
  },
)
export class SharedModule {
}
