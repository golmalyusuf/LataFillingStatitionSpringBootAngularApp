import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from 'src/app/user/user.component';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  baseUrl:string = 'http://localhost:8080/lata';

  constructor(private http:HttpClient) { }

  retriveAllUsersAtOnce(){
    return this.http.get<User[]>(this.baseUrl+'/users'); 
  }

  editUser(id, user){ 
    console.log(" Edit User service EDIT ");
    return this.http.put(this.baseUrl+`/edit-user/${id}`, user);
  }

  retriveUserById(id){
    return this.http.get<User>(this.baseUrl+`/user/${id}`);
  }

  addUser(user){
    console.log("--->>"+this.baseUrl+'/save-user');
    console.log("USER "+user.created_by+"  "+ user.createdAt+"  "+ user.modified_By+" "+ user.updatedAt+"  "+ user.firstName);
    return this.http.post(this.baseUrl+'/save-user',user);
  }
   
  deleteUser(id){
    console.log("Service Delete ROle");
    return this.http.delete(this.baseUrl+`/delete-user/${id}`);
  }
}
