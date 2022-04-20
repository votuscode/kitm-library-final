import { Component, Input } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { BookDto } from '@api/model/bookDto';
import { CategoryDto } from '@api/model/categoryDto';
import { AsyncData } from '~/app/shared/util/async-data';
import { changeDetection } from '~/change-detection.strategy';

@Component({
  selector: 'app-book-form',
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
          <label class="col-lg-3 control-label">Category</label>
          <div class="col-lg-9">
            <select class="form-control">
              <option *ngFor="let category of categories" [innerText]="category.name">
                Category
              </option>
            </select>
          </div>
        </div>
        <div class="form-group mb-3">
          <label class="col-lg-3 control-label">Description</label>
          <div class="col-lg-9">
            <textarea class="form-control" name="description" formControlName="description"></textarea>
          </div>
        </div>
        <div class="form-group mb-3">
          <label class="col-lg-3 control-label">ISBN</label>
          <div class="col-lg-9">
            <input class="form-control" name="isbn" formControlName="isbn"/>
          </div>
        </div>
        <div class="form-group mb-3">
          <label class="col-lg-3 control-label">Image</label>
          <div class="col-lg-9">
            <input class="form-control" name="image" formControlName="image"/>
          </div>
        </div>
        <div class="form-group mb-3">
          <label class="col-lg-3 control-label">Pages</label>
          <div class="col-lg-9">
            <input class="form-control" name="pages" formControlName="pages"/>
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
export class BookFormComponent {
  readonly form = this.fb.group({
    name: [],
    description: [],
    isbn: [],
    image: [],
    pages: [],
  });

  @Input() categories: AsyncData<CategoryDto[]>;

  @Input() set book(value: AsyncData<BookDto>) {
    if (value) {
      this.form.patchValue(value);
    }
  }

  constructor(readonly fb: FormBuilder) {
  }
}
