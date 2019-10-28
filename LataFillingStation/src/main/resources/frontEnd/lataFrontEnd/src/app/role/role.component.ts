import { Component, OnInit } from '@angular/core';

export class Role{
  constructor(
    public id: number,
    public roleName,
    public description,
    public createdBy,
    public createdDate: Date, 
    public modifiedBy,
    public modifiedDate: Date
  ){

  }
}

@Component({
  selector: 'app-role',
  templateUrl: './role.component.html',
  styleUrls: ['./role.component.css']
})
export class RoleComponent implements OnInit {

  constructor() { 
    //Create dummy data
    // for (var i = 0; i < this.collection.count; i++) {
    //   this.collection.data.push(
    //     {
    //       id: i + 1,
    //       value: "items number " + (i + 1)
    //     }
    //   );
    // }
    this.config = {
      itemsPerPage: 5,
      currentPage: 1,
      totalItems: this.roles.length
    };
  }

  ngOnInit() {
  }

  roles = [
    new Role(1, "Admin", "Full Control", "yusuf", new Date(), "Yusuf2", new Date()),
    new Role(2, "Manager", "Half Control", "yusuf", new Date(), "Yusuf3", new Date()),
    new Role(3, "Staff", "Min Control", "yusuf", new Date(), "Yusuf4", new Date()),
    new Role(1, "Admin", "Full Control", "yusuf", new Date(), "Yusuf2", new Date()),
    new Role(2, "Manager", "Half Control", "yusuf", new Date(), "Yusuf3", new Date()),
    new Role(3, "Staff", "Min Control", "yusuf", new Date(), "Yusuf4", new Date()),
    new Role(1, "Admin", "Full Control", "yusuf", new Date(), "Yusuf2", new Date()),
    new Role(2, "Manager", "Half Control", "yusuf", new Date(), "Yusuf3", new Date()),
    new Role(3, "Staff", "Min Control", "yusuf", new Date(), "Yusuf4", new Date()),
    new Role(1, "Admin", "Full Control", "yusuf", new Date(), "Yusuf2", new Date()),
    new Role(2, "Manager", "Half Control", "yusuf", new Date(), "Yusuf3", new Date()),
    new Role(3, "Staff", "Min Control", "yusuf", new Date(), "Yusuf4", new Date()),
    new Role(1, "Admin", "Full Control", "yusuf", new Date(), "Yusuf2", new Date()),
    new Role(2, "Manager", "Half Control", "yusuf", new Date(), "Yusuf3", new Date()),
    new Role(3, "Staff", "Min Control", "yusuf", new Date(), "Yusuf4", new Date())
  ]

  config: any;
  collection = { count: 60, data: [] };

  pageChanged(event){
    this.config.currentPage = event;
  }
}
