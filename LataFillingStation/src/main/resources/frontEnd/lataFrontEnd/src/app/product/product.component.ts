import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from '../service/product.service';

export class Product {
  constructor(
    public id: number,
    public productName,
    public productDescription,
    public productInStock,
    public productWholesalePrice,
    public Created_By,
    public createdAt: Date,
    public Modified_By,
    public updatedAt: Date
  ) {

  }
}

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  private products: Array<any>;
  id: number = 0;
  message: string;
  private pages: Array<number>;
  private page: number = 0;

  config: any;
  collection = { data: [] };
  
  constructor(
    private productService: ProductService,
    private router: Router
  ) {

  }
  ngOnInit() {
    this.getAllProductsAtOnce();
  }

  getAllProductsAtOnce() {
    return this.productService.retriveAllProductsAtOnce().subscribe(
      response => {
        console.log(response);
        //this.collection.data = response; 
        this.products = response;
        this.config = {
          itemsPerPage: 10,
          currentPage: 1,
          totalItems: this.products.length
        };
      }, error => {
        console.log(error.error.message);
      }
    );
  }

  editProduct(id){
    this.router.navigate(['productpage', id]);
  }

  deleteProduct(id){
    console.log("DELETE User ");
    this.productService.deleteProduct(id).subscribe(
      response => {
        console.log(response);
        this.message = 'Deleted Successfully!';
        this.getAllProductsAtOnce();
      }, error =>{
        this.message = "Item Couldn't be found!"
      }
    );
  }
}
