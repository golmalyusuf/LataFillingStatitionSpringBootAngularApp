import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  message = 'Welcome to our System Mr. ';
  userName = "";
  constructor(private activatedRoute: ActivatedRoute) {
    
   }

  ngOnInit() {
    //console.log(this.activatedRoute.snapshot.params['name']);
    //this.userName = this.activatedRoute.snapshot.params['name'];
  }

}
