import { Routes } from '@angular/router';
import { HomeComponent } from './Components/home/home.component';
import { RegisterComponent } from './Components/register/register.component';
import { LoginComponent } from './Components/login/login.component';
import { PropertyDetailsComponent } from './Components/property-details/property-details.component';
import { ProfileComponent } from './Components/profile/profile.component';
import { UserListComponent } from './Components/user-list/user-list.component';

export const routes: Routes = [
  {path : "", component: UserListComponent},
  {path: "register", component: RegisterComponent},
  {path: "login", component: LoginComponent},
  {path: "detail/:id", component: PropertyDetailsComponent},
  {path: "profile", component: ProfileComponent}
];
