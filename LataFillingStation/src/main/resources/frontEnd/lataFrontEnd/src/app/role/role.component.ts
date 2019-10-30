import { Component, OnInit } from '@angular/core';
import { RolesService } from '../service/data/roles.service';
import { Router } from '@angular/router';

export class Role {
  constructor(
    public id: number,
    public roleName,
    public description,
    public created_By,
    public createdAt: Date,
    public modified_By,
    public updatedAt: Date
  ) {

  }
}

@Component({
  selector: 'app-role',
  templateUrl: './role.component.html',
  styleUrls: ['./role.component.css']
})
export class RoleComponent implements OnInit {
  private roles: Array<any>;
  id: number = 0;
  message: string;
  private pages: Array<number>;
  private page: number = 0;

  config: any;
  collection = { data: [] };
  
  constructor(
    private rolesService: RolesService,
    private router: Router
  ) {

  }

  ngOnInit() {
    this.getAllRolesAtOnce();
 
  }

  getAllRolesAtOnce() {
    return this.rolesService.retriveAllRolesAtOnce().subscribe(
      response => {
        console.log(response);
        //this.collection.data = response; 
        this.roles = response;
        this.config = {
          itemsPerPage: 2,
          currentPage: 1,
          totalItems: this.roles.length
        };
      }, error => {
        console.log(error.error.message);
      }
    );
  }

  getAllRoles() {
    return this.rolesService.retriveAllRoles(this.page).subscribe(
      response => {
        console.log(response);
        this.roles = response['content'];
        this.pages = new Array(response['totalPages']);
      }, error => {
        console.log(error.error.message);
      }
    );
  }


  addRole() {
    this.router.navigate(['rolepage', -1]);
  }

  editRole(id) {
    console.log("EDIT ROLE Component");
    this.router.navigate(['rolepage', id]);
  }

  deleteRole(id) {
    console.log("DELETE ROLE ");
    this.rolesService.deleteRole(id).subscribe(
      response => {
        console.log(response);
        this.message = 'Deleted Successfully!';
        this.getAllRoles();
      }, error =>{
        this.message = "Item Couldn't be found!"
      }
    );
  }


  setPage(i, event: any) {
    event.preventDefault();
    this.page = i;
    this.getAllRoles();
  }
//  Pagination 
  pageChanged(event){
    this.config.currentPage = event;
  }
}
