import { Component, inject, Inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { PropertiesListService } from '../../Service/properties-list.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RequestService } from '../../Service/request.service';
import { AuthService } from '../../Service/auth.service';
import { ReviewsService } from '../../Service/reviews.service';

interface Property{
  id: number,
  title: string,
  description: string,
  country: string,
  city: string,
  roomsNumber: number,
  area: number,
  bathroomsNumber: number,
  availability: boolean,
  price: number,
  guests: number,
  //images: [],
  propertyType: 'APARTMENT' | 'VILLA' | 'HOUSE',
  avrgReview : number,
  features : []
}

interface Reservation{
  startDate: string,
  endDate: string,
  property_id : number
}

interface Review{
  id: number,
  review: number,
  comment: string
}
@Component({
  selector: 'app-property-details',
  imports: [ReactiveFormsModule],
  templateUrl: './property-details.component.html',
  styleUrl: './property-details.component.css'
})
export class PropertyDetailsComponent implements OnInit{
  router = inject(Router);
  propertyId !: number;
  reservation !: FormGroup;
  constructor(private route : ActivatedRoute, private service : PropertiesListService, private fb : FormBuilder, private request : RequestService, private authService : AuthService, private reviewService: ReviewsService) {
    this.reservation = fb.group({
      startDate : ['', [Validators.required]],
      endDate : ['', [Validators.required]],
      property_id : ['', [Validators.required]]
    })
  }
  property !: Property;
  owner : any;
  reviewsList !: Review[];

  ngOnInit(): void {
    this.propertyId = Number(this.route.snapshot.paramMap.get('id'));
    this.service.retrievePropertyById(this.propertyId).subscribe((data)=>{
      this.property = data;
      this.reservation.patchValue({property_id : this.property.id});
      console.log(this.property);
    })
    this.getPropertyOwner(this.propertyId);
    this.getReviewsList(this.propertyId);
  }

  getReviewsList(propertyId : number){
    this.reviewService.getPropertyReviews(propertyId).subscribe((reviews)=>{
      this.reviewsList = reviews;
      console.log(this.reviewsList);
    })
  }

  //used for converting number of review to an array
  createRange(count: number): number[] {
    return Array.from({ length: Math.floor(count) }, (_, i) => i);
  }

  getPropertyOwner(property_id : number):any{
    this.service.getOwnerDataByPropertyId(property_id).subscribe((data)=>{
      this.owner = data;
      console.log(this.owner);
    })
  }

  onSubmit(){
    if(this.authService.isLoggedIn()){
      const data = this.reservation.value as Reservation;
      if(this.reservation.valid){
        this.clickedReservation = !this.clickedReservation;
        this.request.sendRequest(data).subscribe((response)=>{
          console.log(response);
        })
      }
    }else{
      this.router.navigate(['/login']);
    }
  }

  clickedReservation : boolean = false;
}
