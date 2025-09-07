import { Routes } from '@angular/router';
import { HomeComponent } from './Components/home/home.component';
import { RegisterComponent } from './Components/register/register.component';
import { LoginComponent } from './Components/login/login.component';
import { PropertyDetailsComponent } from './Components/property-details/property-details.component';
import { ProfileComponent } from './Components/profile/profile.component';
import { UserListComponent } from './Components/user-list/user-list.component';
import { UpdateUserComponent } from './Components/update-user/update-user.component';
import { authGuard } from './Guard/auth.guard';

export const routes: Routes = [
  {path: "", component: HomeComponent},
  {path: "detail/:id", component: PropertyDetailsComponent},

  {
    path : "users_list",
    component: UserListComponent,
    canActivate : [authGuard],
    data: {roles: ["ADMIN"]}
  },

  {path: "register", component: RegisterComponent},
  {path: "login", component: LoginComponent},

  {
    path: "profile",
    component: ProfileComponent,
    canActivate: [authGuard]
  },
  {
    path: "update_user/:id",
    component: UpdateUserComponent,
    canActivate: [authGuard],
    data: {roles: ["ADMIN"]}
  }
];
