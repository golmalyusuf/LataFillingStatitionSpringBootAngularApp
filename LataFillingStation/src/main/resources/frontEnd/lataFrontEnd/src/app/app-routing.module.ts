import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { LogoutComponent } from './logout/logout.component';
import { UserComponent } from './user/user.component';
import { SalesComponent } from './sales/sales.component';
import { RoleComponent } from './role/role.component';
import { ProductComponent } from './product/product.component';
import { ExpenseComponent } from './expense/expense.component';
import { CustomerComponent } from './customer/customer.component';
import { ErrorComponent } from './error/error.component';
import { RolepageComponent } from './rolepage/rolepage.component';
import { UserpageComponent } from './userpage/userpage.component';

const routes: Routes = [
  { path: '', component: LoginComponent},
  { path: 'login', component: LoginComponent},
  { path: 'welcome/:name', component: WelcomeComponent},
  { path: 'logout', component: LogoutComponent},
  { path: 'user', component: UserComponent},
  { path: 'userpage/:id', component: UserpageComponent},
  { path: 'sales', component: SalesComponent},
  { path: 'role', component: RoleComponent},
  { path: 'rolepage/:id', component: RolepageComponent},
  { path: 'product', component: ProductComponent},
  { path: 'expense', component: ExpenseComponent},
  { path: 'customer', component: CustomerComponent}
  // { path: '**', component: ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
