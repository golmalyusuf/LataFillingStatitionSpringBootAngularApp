import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UsersService } from '../service/users.service';
import { Role } from '../role/role.component';

export class User {
  constructor(
    public id: number,
    public firstName,
    public lastName,
    public emailId,
    public address,
    public mobileNumber, 
    public created_by,
    public createdAt: Date,
    public modified_By,
    public updatedAt: Date,
    public role: Role
  ) {

  }
}
@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  private users: Array<any>;
  id: number = 0;
  message: string;
  private pages: Array<number>;
  private page: number = 0;

  config: any;
  collection = { data: [] };
  
  constructor(
    private usersService: UsersService,
    private router: Router
  ) {

  }
  ngOnInit() {
    this.getAllUsersAtOnce();
  }

  getAllUsersAtOnce() {
    return this.usersService.retriveAllUsersAtOnce().subscribe(
      response => {
        console.log(response);
        //this.collection.data = response; 
        this.users = response;
        this.config = {
          itemsPerPage: 10,
          currentPage: 1,
          totalItems: this.users.length
        };
      }, error => {
        console.log(error.error.message);
      }
    );
  }
}
