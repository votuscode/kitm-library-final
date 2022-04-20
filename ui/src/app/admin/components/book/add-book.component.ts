import { Component } from '@angular/core';
import { changeDetection } from '~/change-detection.strategy';

@Component({
  template: `
    <div class="row">
      <div class="col-lg-8 col-md-7 col-sm-6">
        <h2>Add book</h2>
        <app-book-form>
          <button type="submit" class="btn btn-primary" name="add">Add</button>
        </app-book-form>
      </div>
    </div>
  `,
  changeDetection,
})
export class AddBookComponent {
}
