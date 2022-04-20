import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthorService } from '@api/api/author.service';
import { BookService } from '@api/api/book.service';
import { CategoryService } from '@api/api/category.service';
import { OrderService } from '@api/api/order.service';
import { WishService } from '@api/api/wish.service';
import { AuthorDto } from '@api/model/authorDto';
import { BookDto } from '@api/model/bookDto';
import { CategoryDto } from '@api/model/categoryDto';
import { forkJoin, of } from 'rxjs';
import { map, switchMap, tap, withLatestFrom } from 'rxjs/operators';
import { AuthenticationFacade } from '~/app/core/security/authentication.facade';
import { ToastService } from '~/app/toast.service';
import { changeDetection } from '~/change-detection.strategy';

interface BookVm {
  book: BookDto;
  author: AuthorDto;
  category: CategoryDto;
  canReturn: boolean;
  inWishList: boolean;
}

@Component({
  template: `
    <app-layout>
      <h2>Book page</h2>
      <p>Here you can see the book details.</p>
      <div class="row" *ngIf="vm$ | async as vm">
        <div class="col">
          <div class="card" style="width: 18rem">
            <img class="card-img-top" [src]="vm.book.image" alt="Book">
          </div>
        </div>
        <div class="col">
          <h5 class="card-title">{{ vm.book.name }}</h5>
          <p>
            <span class="badge bg-secondary">{{ vm.author.name }}</span>
            <span class="badge bg-primary ms-1">{{ vm.category.name }}</span>
            <span *ngIf="vm.inWishList" class="badge bg-success">Wish list</span>
          </p>
          <p class="card-text">{{ vm.book.description }}</p>
          <p>
            <ng-template #available>
              <button class="btn btn-outline-success" type="button" (click)="order(vm.book.id)">
                🛍 Order
              </button>
            </ng-template>
            <ng-container *ngIf="vm.book.orderId; else available">
              <ng-template #add>
                <button class="btn btn-outline-success" type="button" [disabled]="vm.inWishList"
                        (click)="wish(vm.book.id)">
                  💝 Wish list
                </button>
              </ng-template>
              <ng-container *ngIf="vm.canReturn; else add">
                <button class="btn btn-outline-warning" type="button" (click)="release(vm.book.orderId)">
                  👍 Return
                </button>
              </ng-container>
            </ng-container>
          </p>
        </div>
      </div>
    </app-layout>
  `,
  changeDetection,
})
export class BookView {

  getOrder = (orderId?: string) => {
    return orderId ? this.orderService.getOrder(orderId) : of(null);
  };

  readonly vm$ = this.bookService.getBook(this.route.snapshot.params.bookId).pipe(
    withLatestFrom(this.authenticationFacade.user$),
    switchMap(([book, user]) => {
      return forkJoin([
        this.wishService.getWishes(),
        this.authorService.getAuthor(book.authorId),
        this.categoryService.getCategory(book.categoryId),
        this.getOrder(book.orderId),
      ]).pipe(
        map(([wishes, author, category, order]): BookVm => {
          const canReturn = order?.userId === user?.id;
          const inWishList = wishes.find(wish => wish.bookId === book.id) !== undefined;

          return { book, author, category, canReturn, inWishList };
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
    readonly wishService: WishService,
    readonly authenticationFacade: AuthenticationFacade,
    readonly toastService: ToastService,
    readonly router: Router,
  ) {
  }

  order = (bookId: string) => {
    this.wishService.getWishes().pipe(
      withLatestFrom(this.authenticationFacade.user$),
      switchMap(([wishes, user]) => {
        if (!user) {
          throw new Error('Not logged in');
        }

        return this.orderService.createOrder({ userId: user.id, bookId }).pipe(
          tap(orderDto => {
            this.toastService.success(`Order ${orderDto.id} completed.`);
            void this.router.navigateByUrl('orders');
          }),
          switchMap(() => {
            const wish = wishes.find(wish => wish.bookId === bookId);
            return wish ? this.wishService.deleteWish(wish.id) : of(null);
          }),
        );
      }),
    ).subscribe();
  };

  release = (orderId: string) => {
    this.orderService.deleteOrder(orderId).pipe(
      tap(() => {
        this.toastService.success(`Thank you for reading the book.`);
        void this.router.navigateByUrl('orders');
      }),
    ).subscribe();
  };

  wish = (bookId: string) => {
    of(bookId).pipe(
      withLatestFrom(this.authenticationFacade.user$),
      switchMap(([bookId, user]) => {
        if (!user) {
          throw new Error('Not logged in');
        }

        return this.wishService.createWish({ userId: user.id, bookId }).pipe(
          tap(() => {
            this.toastService.success(`Book is added to your wish list.`);
            void this.router.navigateByUrl('wish-list');
          }),
        );
      }),
    ).subscribe();
  };
}
