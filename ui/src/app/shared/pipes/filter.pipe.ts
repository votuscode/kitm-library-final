import { Pipe, PipeTransform } from '@angular/core';
import { AsyncData } from '~/app/shared/util/async-data';

@Pipe({ name: 'appFilter' })
export class FilterPipe implements PipeTransform {
  transform<T>(items: AsyncData<T[]>, predicate: (item: T) => boolean) {
    return Array.isArray(items) ? items.filter(predicate) : items;
  }
}
