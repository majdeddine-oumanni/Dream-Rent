import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);

  const token = localStorage.getItem('auth_token');
  const userString = localStorage.getItem("auth_user");

  let role: string | null = null;
  if (userString) {
    try {
      role = JSON.parse(userString).role;
    } catch (e) {
      role = null;
    }
  }

  // if no token → send to login
  if (!token) {
    router.navigate(['/login']);
    return false;
  }

  // check expected roles
  const expectedRoles = route.data['roles'] as string[];
  if (expectedRoles && expectedRoles.length > 0) {
    if (!role || !expectedRoles.includes(role.toUpperCase())) {
      router.navigate(['/']);
      return false;
    }
  }

  return true;
};
