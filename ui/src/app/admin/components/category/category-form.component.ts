import { Component, Input } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { CategoryDto } from '@api/model/categoryDto';
import { AsyncData } from '~/app/shared/util/async-data';
import { changeDetection } from '~/change-detection.strategy';

@Component({
  selector: 'app-category-form',
  template: `
    <form action="#" method="post" class="form-horizontal" [formGroup]="form">
      <fieldset>
        <div class="form-group mb-3">
          <label class="col-lg-3 control-label">Name</label>
          <div class="col-lg-9">
            <input type="text" class="form-control" name="name" formControlName="name"/>
          </div>
        </div>
        <div class="form-group mb-3">
          <label class="col-lg-3 control-label">Description</label>
          <div class="col-lg-9">
            <textarea class="form-control" name="description" formControlName="description"></textarea>
          </div>
        </div>
        <div class="form-group mb-3">
          <div class="col-lg-12 col-lg-offset-3">
            <ng-content></ng-content>
          </div>
        </div>
      </fieldset>
    </form>
  `,
  changeDetection,
})
export class CategoryFormComponent {
  readonly form = this.fb.group({
    name: [],
    description: [],
  });

  @Input() set category(value: AsyncData<CategoryDto>) {
    if (value) {
      this.form.patchValue(value);
    }
  }

  constructor(readonly fb: FormBuilder) {
  }
}
