import { Component, OnInit } from '@angular/core';
import { RolesService } from '../service/data/roles.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Role } from '../role/role.component';
import { User } from '../user/user.component'; 
import { UsersService } from '../service/users.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
 


@Component({
  selector: 'app-userpage',
  templateUrl: './userpage.component.html',
  styleUrls: ['./userpage.component.css']
})
export class UserpageComponent implements OnInit {
  id: number;
  roleList: Role[]; 
  user: User ; 
  tempUser: User ; 
  userForm: FormGroup;
  submitted = false;
    
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
    this.user = new User(this.id, '', '',  '',  '',  '', sessionStorage.getItem('authUser'),new Date(),  sessionStorage.getItem('authUser'),new Date(), 0);
     
    this.userForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      address: ['', Validators.required],
      email: ['', [Validators.required, Validators.email,Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]],
      mobileNumber: ['', [Validators.required, Validators.minLength(11),Validators.pattern('[0-9]+')]],
      role: ['', Validators.required] 
    });

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
 

  get f() { return this.userForm.controls; }

    onSubmit() {
        this.submitted = true;
        
        // stop here if form is invalid
        if (this.userForm.invalid) {
            return;
        }
       // alert("role Id "+this.userForm.controls['role'].value);
        
        let roleId = this.userForm.controls['role'].value;
        let tempRole: Role;
        for(let r of this.roleList){
          if(roleId === r.id){
            tempRole = r;
          }
        }
        
        this.tempUser = new User(this.id, this.userForm.controls['firstName'].value,
          this.userForm.controls['lastName'].value,
          this.userForm.controls['email'].value,
          this.userForm.controls['address'].value,
          this.userForm.controls['mobileNumber'].value,
          sessionStorage.getItem('authUser'),new Date(), 
          sessionStorage.getItem('authUser'),new Date(),
          tempRole);
 
          this.userService.addUser(this.tempUser).subscribe(
            response => {
              console.log(response)
              this.router.navigate(['user'])
            }
         );
  }


}
