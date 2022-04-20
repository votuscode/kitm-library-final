import { Component } from '@angular/core';
import { changeDetection } from '~/change-detection.strategy';

@Component({
  selector: 'app-add-category',
  template: `
    <div class="row">
      <div class="col-lg-8 col-md-7 col-sm-6">
        <h2>Add category</h2>
        <app-category-form>
          <button type="submit" class="btn btn-primary" name="add">Add</button>
        </app-category-form>
      </div>
    </div>
  `,
  changeDetection,
})
export class AddCategoryComponent {
}
