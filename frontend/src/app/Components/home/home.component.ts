import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from "@angular/router";
import { PropertiesListService } from '../../Service/properties-list.service';

@Component({
  selector: 'app-home',
  imports: [CommonModule, RouterLink, CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{
  constructor(private service : PropertiesListService) {}

  properties : any = [];
  ngOnInit(): void {
      this.service.retrieveAllProperties().subscribe((data)=>{
        this.properties = data;
        console.log(this.properties);
      })
  }
  createRange(count: number): number[] {
    return Array.from({ length: Math.floor(count) }, (_, i) => i);
  }
}
