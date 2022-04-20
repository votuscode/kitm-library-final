import { Component, OnInit } from '@angular/core';
import { CategoryDto } from '@api/model/categoryDto';
import { AdminFacade } from '~/app/admin/admin.facade';
import { ItemListOptions } from '~/app/admin/components/item-list/item-list.component';
import { changeDetection } from '~/change-detection.strategy';

@Component({
  selector: 'app-category-list',
  template: `
    <app-item-list header="Categories" description="Categories page" [items]="adminFacade.categories$ | async" [options]="options">
      <button type="button" class="btn btn-primary" name="button" routerLink="/admin/categories/add">
        Add category
      </button>
    </app-item-list>
  `,
  changeDetection,
})
export class CategoryListComponent implements OnInit {

  options: ItemListOptions<CategoryDto> = {
    mapper: ({ id, name, description, books }) => {
      return {
        id,
        name,
        description,
        link: `/admin/categories/${id}`,
        info: `Books: ${books}`,
      };
    },
  };

  constructor(readonly adminFacade: AdminFacade) {
  }

  ngOnInit() {
    this.adminFacade.getCategories();
  }
}
