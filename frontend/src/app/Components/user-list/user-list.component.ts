import { Component, OnInit } from '@angular/core';
import { NgClass } from "@angular/common";
import { UsersService } from '../../Service/users.service';

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
  imports: [NgClass],
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.css'
})

export class UserListComponent implements OnInit{
  constructor(private service: UsersService) {}
  users !: Users[];

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers(){
    this.service.getUsers().subscribe((data)=>{
      this.users = data;
    })
  }

  deleteUser(id:number){
    this.service.deleteUser(id).subscribe(()=>{
      const confirmed = confirm("Are you sure you want to delete this user?");
      if(confirmed){
        this.users = this.users.filter(user => user.id !== id)
      }
    });
  }
}
