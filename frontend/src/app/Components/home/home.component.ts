import { Component } from '@angular/core';
import { UsersService } from '../../Service/users.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-home',
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  constructor(private service : UsersService){}

  private ngOnInit():void{
    this.getUsers();
  }

  users:any[] = [];
  getUsers(){
    this.service.getUsers().subscribe((data:any)=>{
      this.users = data
    })
  }
}
