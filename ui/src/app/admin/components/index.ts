import { AdminView } from '~/app/admin/components/admin.view';
import { LayoutComponent } from '~/app/admin/components/layout/layout.component';
import { ItemListComponent } from '~/app/admin/components/item-list/item-list.component';
import { ItemListNavigationComponent } from '~/app/admin/components/item-list/item-list-navigation.component';
// author
import { AuthorListComponent } from '~/app/admin/components/author/author-list.component';
import { AuthorFormComponent } from '~/app/admin/components/author/author-form.component';
import { AddAuthorComponent } from '~/app/admin/components/author/add-author.component';
import { UpdateAuthorComponent } from '~/app/admin/components/author/update-author.component';
// book
import { BookListComponent } from '~/app/admin/components/book/book-list.component';
import { BookFormComponent } from '~/app/admin/components/book/book-form.component';
import { AddBookComponent } from '~/app/admin/components/book/add-book.component';
import { UpdateBookComponent } from '~/app/admin/components/book/update-book.component';
// category
import { CategoryListComponent } from '~/app/admin/components/category/category-list.component';
import { CategoryFormComponent } from '~/app/admin/components/category/category-form.component';
import { AddCategoryComponent } from '~/app/admin/components/category/add-category.component';
import { UpdateCategoryComponent } from '~/app/admin/components/category/update-category.component';
// role
import { RoleListComponent } from '~/app/admin/components/role/role-list.component';
// user
import { UserListComponent } from '~/app/admin/components/user/user-list.component';

export const components = [
  AdminView,
  LayoutComponent,
  ItemListComponent,
  ItemListNavigationComponent,
  // role
  RoleListComponent,
  // user
  UserListComponent,
  // author
  AuthorListComponent,
  AuthorFormComponent,
  AddAuthorComponent,
  UpdateAuthorComponent,
  // category
  CategoryListComponent,
  CategoryFormComponent,
  AddCategoryComponent,
  UpdateCategoryComponent,
  // book
  BookListComponent,
  BookFormComponent,
  AddBookComponent,
  UpdateBookComponent,
];

export {
  AdminView,
  // role
  RoleListComponent,
  // user
  UserListComponent,
  // author
  AuthorListComponent,
  AddAuthorComponent,
  UpdateAuthorComponent,
  // book
  BookListComponent,
  AddBookComponent,
  UpdateBookComponent,
  // category
  CategoryListComponent,
  AddCategoryComponent,
  UpdateCategoryComponent,
};
