import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from './../service/authentication.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = "";
  userpass = "";
  errorMessage = "User name or password is not correct";
  inValidLogin = false;

  constructor(private router: Router, private auth:AuthenticationService ) { 

  }

  ngOnInit() {
  }

  handleLogin(){ 
    if(this.auth.authenticate(this.username, this.userpass)){ 
      this.router.navigate(['welcome', this.username]);
      this.inValidLogin = false;
    }else{
      this.inValidLogin = true;
    }
  }
}
