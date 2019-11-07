import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../service/product.service';
import { FormBuilder } from '@angular/forms';
import { Product } from '../product/product.component';

@Component({
  selector: 'app-productpage',
  templateUrl: './productpage.component.html',
  styleUrls: ['./productpage.component.css']
})
export class ProductpageComponent implements OnInit {
  id: number;
  product: Product;
  
  constructor(
    private activatedRoute: ActivatedRoute,
    private productService: ProductService,
    private router: Router,
    public fb: FormBuilder
  ) { }

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params['id']; 
    this.product = new Product(this.id, '', '','','', sessionStorage.getItem('authUser'),new Date(),  sessionStorage.getItem('authUser'),new Date());
     
    if (this.id != -1) {
      this.productService.retriveProductById(this.id).subscribe(
        response => {
          this.product = response;
           
          console.log(response);
        }
      );
    }
  }
  saveProduct(){ 
      this.product.Modified_By = sessionStorage.getItem('authUser');
      this.product.updatedAt = new Date(); 
       
      if (this.id != -1) {
        console.log();
        this.productService.editProduct(this.id, this.product).subscribe(
          response => {
            console.log(response)
            this.router.navigate(['product'])
          }
        );
  
      } else {
        this.product.Created_By = sessionStorage.getItem('authUser');
        this.product.createdAt = new Date();
        console.log("product name "+this.product.productName +" description "+this.product.productDescription);
        this.productService.addProduct(this.product).subscribe(
           response => {
             console.log(response)
             this.router.navigate(['product'])
           }
        );
      }
    }

}
