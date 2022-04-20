import { Injectable } from '@angular/core';
import { AuthenticationService } from '@api/api/authentication.service';
import { AuthorService } from '@api/api/author.service';
import { BookService } from '@api/api/book.service';
import { CategoryService } from '@api/api/category.service';
import { RoleService } from '@api/api/role.service';
import { UserService } from '@api/api/user.service';
import { AuthorDto } from '@api/model/authorDto';
import { BookDto } from '@api/model/bookDto';
import { CategoryDto } from '@api/model/categoryDto';
import { RoleDto } from '@api/model/roleDto';
import { UserDto } from '@api/model/userDto';
import { BehaviorSubject, forkJoin } from 'rxjs';
import { take, tap } from 'rxjs/operators';
import { asMap } from '~/app/shared/util/as-map';
import { ToastService } from '~/app/toast.service';

const next = <T>(subject: BehaviorSubject<T>) => tap((data: T) => subject.next(data));

export interface BookVm extends BookDto {
  relations: {
    author: AuthorDto;
    category: CategoryDto;
  };
}

@Injectable()
export class AdminFacade {
  readonly roles$ = new BehaviorSubject<RoleDto[]>([]);
  readonly users$ = new BehaviorSubject<UserDto[]>([]);
  readonly authors$ = new BehaviorSubject<AuthorDto[]>([]);
  readonly categories$ = new BehaviorSubject<CategoryDto[]>([]);
  readonly books$ = new BehaviorSubject<BookVm[]>([]);

  constructor(
    readonly roleService: RoleService,
    readonly userService: UserService,
    readonly authorService: AuthorService,
    readonly categoryService: CategoryService,
    readonly bookService: BookService,
    readonly authenticationService: AuthenticationService,
    readonly toastService: ToastService,
  ) {
  }

  getRoles = () => this.roleService.getRoles().pipe(next(this.roles$)).subscribe();

  getUsers = () => this.userService.getUsers().pipe(next(this.users$)).subscribe();

  getAuthors = () => this.authorService.getAuthors().pipe(next(this.authors$)).subscribe();

  getAuthor = (id: string) => this.authorService.getAuthor(id);

  getCategories = () => this.categoryService.getCategories().pipe(next(this.categories$)).subscribe();

  getCategory = (id: string) => this.categoryService.getCategory(id).pipe(take(1));

  getBooks = () => {
    forkJoin([
      this.authorService.getAuthors(),
      this.categoryService.getCategories(),
      this.bookService.getBooks(),
    ]).pipe(
      tap(([authors, categories, books]) => {
        const authorsMap = asMap(authors);
        const categoriesMap = asMap(categories);

        const vms = books.map((book): BookVm => {
          const author = authorsMap[book.authorId];
          const category = categoriesMap[book.categoryId];
          return { ...book, relations: { author, category } };
        });

        this.books$.next(vms);
      }),
    ).subscribe();
  };

  getBook = (id: string) => this.bookService.getBook(id);
}
