import { Component } from '@angular/core';
import { AuthorService } from '@api/api/author.service';
import { BookService } from '@api/api/book.service';
import { CategoryService } from '@api/api/category.service';
import { BehaviorSubject, forkJoin } from 'rxjs';
import { map } from 'rxjs/operators';
import { BookVm } from '~/app/shared/components/book-list/book-list.component';
import { asMap } from '~/app/shared/util/as-map';
import { changeDetection } from '~/change-detection.strategy';

@Component({
  template: `
    <app-layout>
      <div widget>
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search"
               (input)="search(searchInput.value)" #searchInput>
      </div>
      <app-book-list [vms]="vm$ | async" [search]="search$ | async"></app-book-list>
    </app-layout>
  `,
  changeDetection,
})
export class HomeView {

  readonly search$ = new BehaviorSubject<string>('');

  readonly vm$ = forkJoin([
    this.bookService.getBooks(),
    this.authorService.getAuthors(),
    this.categoryService.getCategories(),
  ]).pipe(
    map(([books, authors, categories]) => {
      const authorMap = asMap(authors);
      const categoryMap = asMap(categories);

      return books.map((book): BookVm => {
        return {
          book,
          author: authorMap[book.authorId],
          category: categoryMap[book.categoryId],
          link: `/books/${book.id}`,
          ordered: Boolean(book.orderId),
        };
      });
    }),
  );

  constructor(
    readonly bookService: BookService,
    readonly authorService: AuthorService,
    readonly categoryService: CategoryService,
  ) {
  }

  search = (value: string) => {
    this.search$.next(value);
  };
}
