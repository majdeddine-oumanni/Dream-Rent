import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from "@angular/router";
import { AuthService } from '../../Service/auth.service';

@Component({
  selector: 'app-navbar',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit{
  constructor(public service : AuthService) {}
  user :any;
  ngOnInit(): void {
    this.user = JSON.parse(this.service.getUserData());
  }
}
