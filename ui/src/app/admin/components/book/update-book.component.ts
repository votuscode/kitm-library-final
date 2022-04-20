import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AdminFacade } from '~/app/admin/admin.facade';
import { changeDetection } from '~/change-detection.strategy';

@Component({
  template: `
    <div class="row">
      <div class="col-lg-8 col-md-7 col-sm-6">
        <h2>Update book</h2>
        <app-book-form [book]="book$ | async" [categories]="adminFacade.categories$ | async">
          <button type="submit" class="btn btn-warning" name="update">Update</button>
          <button type="submit" class="btn btn-danger m-1" name="delete">Delete</button>
        </app-book-form>
      </div>
    </div>
  `,
  changeDetection,
})
export class UpdateBookComponent implements OnInit {
  readonly book$ = this.adminFacade.getBook(this.route.snapshot.params.id);

  constructor(readonly adminFacade: AdminFacade, readonly route: ActivatedRoute) {
  }

  ngOnInit() {
    this.adminFacade.getCategories();
  }
}
