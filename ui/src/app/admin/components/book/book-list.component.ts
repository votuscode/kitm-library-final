import { Component, OnInit } from '@angular/core';
import { AdminFacade, BookVm } from '~/app/admin/admin.facade';
import { ItemListOptions } from '~/app/admin/components/item-list/item-list.component';
import { changeDetection } from '~/change-detection.strategy';

@Component({
  selector: 'app-category-list',
  template: `
    <app-item-list header="Books" description="Books page" [items]="adminFacade.books$ | async" [options]="options">
      <button type="button" class="btn btn-primary" name="button" routerLink="/admin/books/add">
        Add book
      </button>
    </app-item-list>
  `,
  changeDetection,
})
export class BookListComponent implements OnInit {

  options: ItemListOptions<BookVm> = {
    mapper: ({ id, name, description, relations }) => {
      return {
        id,
        name,
        description,
        link: `/admin/books/${id}`,
        info: `Author: ${relations.author.name}, category: ${relations.category.name}`,
      };
    },
  };

  constructor(readonly adminFacade: AdminFacade) {
  }

  ngOnInit() {
    this.adminFacade.getBooks();
  }
}
