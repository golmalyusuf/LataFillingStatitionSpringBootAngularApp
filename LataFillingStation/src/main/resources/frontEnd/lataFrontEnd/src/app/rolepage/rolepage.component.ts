import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { RolesService } from '../service/data/roles.service';
import { Role } from '../role/role.component';

@Component({
  selector: 'app-rolepage',
  templateUrl: './rolepage.component.html',
  styleUrls: ['./rolepage.component.css']
})
export class RolepageComponent implements OnInit {
  id: number;
  role: Role;
  
  constructor(
    private activatedRoute: ActivatedRoute,
    private rolesService: RolesService,
    private router: Router,
    public fb: FormBuilder
  ) { }

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params['id']; 
    this.role = new Role(this.id, '', '',  sessionStorage.getItem('authUser'),new Date(),  sessionStorage.getItem('authUser'),new Date());
     
    if (this.id != -1) {
      this.rolesService.retriveRoleById(this.id).subscribe(
        response => {
          this.role = response;
           
          console.log(response);
        }
      );
    }
  }
  saveRole(){ 
      this.role.updatedBy = sessionStorage.getItem('authUser');
      this.role.updatedAt = new Date(); 
       
      if (this.id != -1) {
        console.log();
        this.rolesService.editRole(this.id, this.role).subscribe(
          response => {
            console.log(response)
            this.router.navigate(['role'])
          }
        );
  
      } else {
        this.role.createdBy = sessionStorage.getItem('authUser');
        this.role.createdAt = new Date();
        this.role.updatedBy = sessionStorage.getItem('authUser');
        this.role.updatedAt = new Date();
        
        this.rolesService.addRole(this.role).subscribe(
           response => {
             console.log(response)
             this.router.navigate(['role'])
           }
        );
      }
    }
  
}
