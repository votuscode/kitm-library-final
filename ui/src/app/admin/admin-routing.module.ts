import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthenticationGuard } from '~/app/core/security/authentication.guard';
// feature
import * as fromComponents from './components';

const routes: Routes = [
  {
    path: '', canActivate: [AuthenticationGuard], component: fromComponents.AdminView, children: [
      { path: 'roles', component: fromComponents.RoleListComponent },
      { path: 'users', component: fromComponents.UserListComponent },
      { path: 'authors', component: fromComponents.AuthorListComponent },
      { path: 'authors/add', component: fromComponents.AddAuthorComponent },
      { path: 'authors/:id', component: fromComponents.UpdateAuthorComponent },
      { path: 'categories', component: fromComponents.CategoryListComponent },
      { path: 'categories/add', component: fromComponents.AddCategoryComponent },
      { path: 'categories/:id', component: fromComponents.UpdateCategoryComponent },
      { path: 'books', component: fromComponents.BookListComponent },
      { path: 'books/add', component: fromComponents.AddBookComponent },
      { path: 'books/:id', component: fromComponents.UpdateBookComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule {
}
