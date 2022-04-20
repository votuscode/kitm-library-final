import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AdminFacade } from '~/app/admin/admin.facade';
import { changeDetection } from '~/change-detection.strategy';

@Component({
  template: `
    <div class="row">
      <div class="col-lg-8 col-md-7 col-sm-6">
        <h2>Update author</h2>
        <app-author-form [author]="author$ | async">
          <button type="submit" class="btn btn-warning" name="update">Update</button>
          <button type="submit" class="btn btn-danger m-1" name="delete">Delete</button>
        </app-author-form>
      </div>
    </div>
  `,
  changeDetection,
})
export class UpdateAuthorComponent {
  readonly author$ = this.adminFacade.getAuthor(this.route.snapshot.params.id);

  constructor(readonly adminFacade: AdminFacade, readonly route: ActivatedRoute) {
  }

  ngOnInit() {
    this.adminFacade.getCategories();
  }
}
