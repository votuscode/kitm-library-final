import { Component, OnInit } from '@angular/core';
import { UserDto } from '@api/model/userDto';
import { AdminFacade } from '~/app/admin/admin.facade';
import { ItemListOptions } from '~/app/admin/components/item-list/item-list.component';
import { changeDetection } from '~/change-detection.strategy';

@Component({
  template: `
    <app-item-list header="Users" description="Users page" [items]="adminFacade.users$ | async" [options]="options">
      <button type="button" class="btn btn-primary" name="button" routerLink="/admin/users/add">
        Add user
      </button>
    </app-item-list>
  `,
  changeDetection,
})
export class UserListComponent implements OnInit{

  options: ItemListOptions<UserDto> = {
    mapper: ({ id, name, username, email }) => {
      return {
        id,
        name,
        description: `${username} (${email})`,
        link: `/admin/users/${id}`,
        info: `Value: ${123}, value: ${123}`,
      };
    },
  };

  constructor(readonly adminFacade: AdminFacade) {
  }

  ngOnInit() {
    this.adminFacade.getUsers();
  }
}
