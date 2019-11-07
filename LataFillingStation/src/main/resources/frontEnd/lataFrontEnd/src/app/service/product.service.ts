import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from '../product/product.component';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  baseUrl:string = 'http://localhost:8080/lata';

  constructor(private http:HttpClient) { }

  retriveAllProductsAtOnce(){
    return this.http.get<Product[]>(this.baseUrl+'/products'); 
  }

  editProduct(id, product){ 
    console.log(" Edit Product service EDIT ");
    return this.http.put(this.baseUrl+`/edit-product/${id}`, product);
  }

  retriveProductById(id){
    return this.http.get<Product>(this.baseUrl+`/product/${id}`);
  }

  addProduct(product:Product){
    console.log("--->>"+this.baseUrl+'/save-product');
    console.log("Product "+product.Created_By+"  "+product.createdAt+"  "+ product.updatedAt+"  "+ product.Modified_By+"  name "+ product.productName+"  des "+product.productDescription+" stock "+product.productInStock+" wprice "+product.productWholesalePrice);
    return this.http.post(this.baseUrl+`/save-product`,product);
  }
   
  deleteProduct(id){
    console.log("Service Delete Product");
    return this.http.delete(this.baseUrl+`/delete-product/${id}`);
  }
}
