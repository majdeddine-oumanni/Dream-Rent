import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
   // Get token from localStorage
  const token = localStorage.getItem('auth_token');


  // If request URL matches a public one → skip token
  if (req.method === 'GET' && req.url.startsWith('/api/properties')) {
    return next(req);
  }

  // If we have a token, clone the request and add the Authorization header
  if (token) {
    const cloned = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
    return next(cloned);
  }

  // If no token, just send the request as is
  return next(req);
};
