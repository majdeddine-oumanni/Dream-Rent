import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

interface Review{
  id: number,
  review: number,
  comment: string
}

@Injectable({
  providedIn: 'root'
})
export class ReviewsService {
  private url = 'http://localhost:8080/api/reviews';
  constructor(private http: HttpClient) { }

  getPropertyReviews(propertyId: number):Observable<Review[]>{
    return this.http.get<Review[]>(`${this.url}/property/${propertyId}`);
  }

}
