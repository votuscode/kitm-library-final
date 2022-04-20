import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthorService } from '@api/api/author.service';
import { BookService } from '@api/api/book.service';
import { CategoryService } from '@api/api/category.service';
import { OrderService } from '@api/api/order.service';
import { forkJoin, of } from 'rxjs';
import { map, switchMap } from 'rxjs/operators';
import { AuthenticationFacade } from '~/app/core/security/authentication.facade';
import { BookVm } from '~/app/shared/components/book-list/book-list.component';
import { asMap } from '~/app/shared/util/as-map';
import { ToastService } from '~/app/toast.service';
import { changeDetection } from '~/change-detection.strategy';

@Component({
  template: `
    <app-layout>
      <h2>Orders page</h2>
      <p>Here you can see your orders.</p>
      <app-book-list [vms]="vm$ | async"></app-book-list>
    </app-layout>
  `,
  changeDetection,
})
export class OrdersView {

  readonly vm$ = forkJoin([
    this.orderService.getOrders(),
    this.authorService.getAuthors(),
    this.categoryService.getCategories(),
  ]).pipe(
    switchMap(([orders, authors, categories]) => {
      const authorMap = asMap(authors);
      const categoryMap = asMap(categories);

      if (orders.length === 0) {
        return of([]);
      }

      return forkJoin(orders.map(order => this.bookService.getBook(order.bookId))).pipe(
        map((books): BookVm[] => {
          const bookMap = asMap(books);

          return orders.map(order => {
            const book = bookMap[order.bookId];

            return {
              book,
              author: authorMap[book.authorId],
              category: categoryMap[book.categoryId],
              link: `/books/${order.bookId}`,
              ordered: false,
            };
          });
        }),
      );
    }),
  );

  constructor(
    readonly route: ActivatedRoute,
    readonly bookService: BookService,
    readonly authorService: AuthorService,
    readonly categoryService: CategoryService,
    readonly orderService: OrderService,
    readonly authenticationFacade: AuthenticationFacade,
    readonly toastService: ToastService,
  ) {
  }

  search = (value: string) => {
    console.log({ value });
  };
}
