import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthorService } from '@api/api/author.service';
import { BookService } from '@api/api/book.service';
import { CategoryService } from '@api/api/category.service';
import { WishService } from '@api/api/wish.service';
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
      <h2>Wish list</h2>
      <p>Here you can see your wish list.</p>
      <app-book-list [vms]="vm$ | async"></app-book-list>
    </app-layout>
  `,
  changeDetection,
})
export class WishListView {

  readonly vm$ = forkJoin([
    this.wishService.getWishes(),
    this.authorService.getAuthors(),
    this.categoryService.getCategories(),
  ]).pipe(
    switchMap(([wishes, authors, categories]) => {
      const authorMap = asMap(authors);
      const categoryMap = asMap(categories);

      if (wishes.length === 0) {
        return of([]);
      }

      return forkJoin(wishes.map(wish => this.bookService.getBook(wish.bookId))).pipe(
        map((books): BookVm[] => {
          const bookMap = asMap(books);

          return wishes.map(wish => {
            const book = bookMap[wish.bookId];

            return {
              book,
              author: authorMap[book.authorId],
              category: categoryMap[book.categoryId],
              link: `/books/${wish.bookId}`,
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
    readonly wishService: WishService,
    readonly authenticationFacade: AuthenticationFacade,
    readonly toastService: ToastService,
  ) {
  }

  search = (value: string) => {
    console.log({ value });
  };
}
