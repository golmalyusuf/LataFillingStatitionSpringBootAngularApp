import { Component, OnInit } from '@angular/core';
import { RolesService } from '../service/data/roles.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Role } from '../role/role.component';
import { User } from '../user/user.component';
import { FormBuilder } from '@angular/forms';
import { UsersService } from '../service/users.service';


@Component({
  selector: 'app-userpage',
  templateUrl: './userpage.component.html',
  styleUrls: ['./userpage.component.css']
})
export class UserpageComponent implements OnInit {
  id: number;
  roleList: Role[];
  
  user: User ; 

  constructor(
    private activatedRoute: ActivatedRoute,
    private rolesService: RolesService, 
    private userService: UsersService,
    private router: Router,
    public fb: FormBuilder
  ) { }

  ngOnInit() {
    this.getAllRolesAtOnce();
    console.log("ROLE LENGTH "+this.roleList);
    this.id = this.activatedRoute.snapshot.params['id'];
    this.user = new User(this.id, '', '',  '',  '',  '', '',new Date(),  'yusuf1',new Date(), this.roleList);
     
    if (this.id != -1) {
      this.userService.retriveUserById(this.id).subscribe(
        response => {
          this.user = response;
          console.log(response);
        }
      );
   }
  }

  getAllRolesAtOnce() {
    return this.rolesService.retriveAllRolesAtOnce().subscribe(
      response => {
        console.log(response); 
        this.roleList = response; 
      }, error => {
        console.log(error.error.message);
      }
    );
  }


  saveUser(){ 
    this.user.modified_By = sessionStorage.getItem('authUser');
    this.user.updatedAt = new Date(); 
     
    if (this.id != -1) {
      console.log();
      this.userService.editUser(this.id, this.user).subscribe(
        response => {
          console.log(response)
          this.router.navigate(['user'])
        }
      );

    } else {
      this.user.created_by = sessionStorage.getItem('authUser');
      this.user.createdAt = new Date();
      
      this.userService.addUser(this.user).subscribe(
         response => {
           console.log(response)
           this.router.navigate(['user'])
         }
      );
    }
  }
}
