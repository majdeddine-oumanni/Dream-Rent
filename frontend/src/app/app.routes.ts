import { Routes } from '@angular/router';
import { HomeComponent } from './Components/home/home.component';
import { RegisterComponent } from './Components/register/register.component';

export const routes: Routes = [
  {path : "", component: HomeComponent},
  {path: "form", component: RegisterComponent}
];
