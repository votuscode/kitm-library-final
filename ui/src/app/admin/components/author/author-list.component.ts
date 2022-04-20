import { Component, OnInit } from '@angular/core';
import { AuthorDto } from '@api/model/authorDto';
import { AdminFacade } from '~/app/admin/admin.facade';
import { ItemListOptions } from '~/app/admin/components/item-list/item-list.component';
import { changeDetection } from '~/change-detection.strategy';

@Component({
  selector: 'app-category-list',
  template: `
    <app-item-list header="Authors" description="Authors page" [items]="adminFacade.authors$ | async" [options]="options">
      <button type="button" class="btn btn-primary" name="button" routerLink="/admin/authors/add">
        Add author
      </button>
    </app-item-list>
  `,
  changeDetection,
})
export class AuthorListComponent implements OnInit {

  options: ItemListOptions<AuthorDto> = {
    mapper: ({ id, name, description, books }) => {
      return {
        id,
        name,
        description,
        link: `/admin/authors/${id}`,
        info: `Books: ${books}`,
      };
    },
  };

  constructor(readonly adminFacade: AdminFacade) {
  }

  ngOnInit() {
    this.adminFacade.getAuthors();
  }
}
