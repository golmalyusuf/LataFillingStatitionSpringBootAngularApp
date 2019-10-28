import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor() { }

  authenticate(username, password){
    if(username === 'yusuf' && password === '123'){
      sessionStorage.setItem('authUser', username);
      return true;
    }else false;
  }

  isUserLoggedIn(){
    let user = sessionStorage.getItem('authUser');
    return !(user === null);
  }
}
