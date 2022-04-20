import { Component, Input } from '@angular/core';
import { AsyncData } from '~/app/shared/util/async-data';
import { changeDetection } from '~/change-detection.strategy';

export interface ItemListOptions<T> {
  mapper: (data: T) => ListItem;
}

export interface ListItem {
  id: string;
  name: string;
  badge?: string;
  description?: string;
  info: string;
  link: string;
}

@Component({
  selector: 'app-item-list',
  template: `
    <ng-template #loading>
      <p>Loading...</p>
    </ng-template>

    <div class="row mb-3">
      <div class="col">
        <div class="row">
          <div class="col">
            <h2>{{ header }}</h2>
            <p>{{ description }}</p>
          </div>
          <div class="col-auto">
            <ng-content></ng-content>
          </div>
        </div>

        <ul class="list-group" *ngIf="options && items; else loading">
          <a [routerLink]="item.link"
             class="list-group-item list-group-item-action flex-column align-items-start"
             *ngFor="let item of items.map(options.mapper)">
            <div class="d-flex w-100 justify-content-between">
              <h5 class="mb-1" [innerText]="item.name">Name</h5>
              <small *ngIf="item.badge" [innerText]="item.badge">Badge</small>
            </div>
            <p class="mb-1" [innerText]="item.description">Description</p>
            <small [innerText]="item.info">Info</small>
          </a>
        </ul>

        <app-item-list-navigation></app-item-list-navigation>
      </div>
    </div>
  `,
  changeDetection,
})
export class ItemListComponent<T> {

  @Input() items: AsyncData<T[]>;
  @Input() options!: ItemListOptions<T>;
  @Input() header?: string;
  @Input() description?: string;
}
