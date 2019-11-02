import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Role } from 'src/app/role/role.component';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RolesService {

  constructor(private http:HttpClient) { }

  baseUrl:string = 'http://localhost:8080/lata';

  retriveAllRolesAtOnce(){
    return this.http.get<Role[]>(this.baseUrl+'/roles'); 
  }

  retriveAllRoles(page:number){
    return this.http.get<Role[]>(this.baseUrl+'/allroles?page='+page+'&size=2'); 
  }
 
  deleteRole(id:number){ 
    console.log("Service Delete ROle");
    return this.http.delete(this.baseUrl+`/delete-role/${id}`)
  }

  editRole(id, role){ 
    console.log(" Edit Role service EDIT ");
    return this.http.put(this.baseUrl+`/edit-role/${id}`, role);
  }

  retriveRoleById(id):Observable<any>{
    return this.http.get<Role>(this.baseUrl+`/role/${id}`);
  }

  addRole(role){
    return this.http.post(this.baseUrl+'/save-role',role);
  }
}
