import { Component, OnInit } from '@angular/core';
import { NgClass } from "@angular/common";
import { UsersService } from '../../Service/users.service';
import { RouterLink } from "@angular/router";

interface Users{
  id:number,
  firstName: string,
  lastName : string,
  email: string,
  role : string,
  country: string,
  phone : string
}

@Component({
  selector: 'app-user-list',
  imports: [NgClass, RouterLink],
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.css'
})

export class UserListComponent implements OnInit{
  constructor(private service: UsersService) {}
  users !: Users[];
  usersTotalNumber !: number;
  adminsNumber !: number;
  tenantsNumber !: number;
  ownersNumber !: number;

  ngOnInit(): void {
    this.getUsers();
    this.getUsersNumber();
    this.getUsersNumberByRole("ADMIN");
    this.getUsersNumberByRole("TENANT");
    this.getUsersNumberByRole("OWNER");
  }

  getUsers(){
    this.service.getUsers().subscribe((data)=>{
      this.users = data;
    })
  }

  deleteUser(id: number) {
    const confirmed = confirm("Are you sure you want to delete this user?");
    if (confirmed) {
      this.service.deleteUser(id).subscribe(() => {
        this.users = this.users.filter(user => user.id !== id);
      });
    }
  }

  getUsersNumber(){
    this.service.getUsersNumber().subscribe((num)=>{
      this.usersTotalNumber = num;
    })
  }

  getUsersNumberByRole(role:string){
    this.service.getUsersNumberByRole(role).subscribe((num)=>{
      if(role == "ADMIN"){
        this.adminsNumber = num;
      }else if(role == "TENANT"){
        this.tenantsNumber = num;
      }else if(role == "OWNER"){
        this.ownersNumber = num;
      }
    })
  }
}
