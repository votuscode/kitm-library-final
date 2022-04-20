import { Component, OnInit } from '@angular/core';
import { changeDetection } from '~/change-detection.strategy';
import { RoleDto } from '@api/model/roleDto';
import { AdminFacade } from '~/app/admin/admin.facade';
import { ItemListOptions } from '~/app/admin/components/item-list/item-list.component';

@Component({
  template: `
    <app-item-list header="Roles" description="Roles page" [items]="adminFacade.roles$ | async" [options]="options">
      <button type="button" class="btn btn-primary" name="button" routerLink="/admin/roles/add">
        Add role
      </button>
    </app-item-list>
  `,
  changeDetection,
})
export class RoleListComponent implements OnInit {

  options: ItemListOptions<RoleDto> = {
    mapper: ({ id, name }) => {
      return {
        id,
        name,
        description: '',
        link: `/admin/users/${id}`,
        info: `Value: ${123}, value: ${123}`,
      };
    },
  };

  constructor(readonly adminFacade: AdminFacade) {
  }

  ngOnInit() {
    this.adminFacade.getRoles();
  }
}
