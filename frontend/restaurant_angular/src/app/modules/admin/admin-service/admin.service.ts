import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from 'src/app/auth-service/storage-service/storage.service';

const BASIC_URL = ["http://localhost:8088/"]

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }
//category
  postCategory(categoryDto: any): Observable<any> {
    return this.http.post<[]>(BASIC_URL + "api/admin/category", categoryDto,{
      headers:this.createAuthorizationHeader()
    });
  }
  getAllCategory(): Observable<any> {
    return this.http.get<[]>(BASIC_URL + "api/admin/categories", {
      headers:this.createAuthorizationHeader()
    });
  }
  getAllCategoryByTitle(title:string): Observable<any> {
    return this.http.get<[]>(BASIC_URL + `api/admin/categories/${title}`, {
      headers:this.createAuthorizationHeader()
    });
  }
  getCategoryById(categoryId:number): Observable<any> {
    return this.http.get<[]>(BASIC_URL + `api/admin/category/${categoryId}`, {
      headers:this.createAuthorizationHeader()
    });
  }
  updateCategory(categoryId: number, categoryDto:any): Observable<any> {
    debugger
    return this.http.put<[]>(BASIC_URL + `api/admin/category/${categoryId}`, categoryDto,{
      headers:this.createAuthorizationHeader()
    });
  }
  deleteCategory(categoryId:number): Observable<any> {
    return this.http.delete<[]>(BASIC_URL + `api/admin/category/${categoryId}`, {
      headers:this.createAuthorizationHeader()
    });
  }
  // Product
  postProduct(categoryId: number, productDto:any): Observable<any> {
    return this.http.post<[]>(BASIC_URL + `api/admin/${categoryId}/product`, productDto,{
      headers:this.createAuthorizationHeader()
    });
  }
  getProductByCategory(categoryId:number): Observable<any> {
    return this.http.get<[]>(BASIC_URL + `api/admin/${categoryId}/products`, {
      headers:this.createAuthorizationHeader()
    });
  }
  getProductByCategoryAndTitle(categoryId:number, title:string): Observable<any> {
    return this.http.get<[]>(BASIC_URL + `api/admin/${categoryId}/product/${title}`, {
      headers:this.createAuthorizationHeader()
    });
  }
  deleteProduct(productId:number): Observable<any> {
    return this.http.delete<[]>(BASIC_URL + `api/admin/product/${productId}`, {
      headers:this.createAuthorizationHeader()
    });
  }
  getProductById(productId:number): Observable<any> {
    return this.http.get<[]>(BASIC_URL + `api/admin/product/${productId}`, {
      headers:this.createAuthorizationHeader()
    });
  }
  updateProduct(productId: number, productDto:any): Observable<any> {
    debugger
    return this.http.put<[]>(BASIC_URL + `api/admin/product/${productId}`, productDto,{
      headers:this.createAuthorizationHeader()
    });
  }
  //reservation
  getReservations(): Observable<any> {
    return this.http.get<[]>(BASIC_URL + `api/admin/reservations`, {
      headers:this.createAuthorizationHeader()
    });
  }
  changeReservationStatus(reservationId: number, status:string): Observable<any> {
    return this.http.get<[]>(BASIC_URL + `api/admin/reservation/${reservationId}/${status}`, {
      headers:this.createAuthorizationHeader()
    });
  }
  //Authen và Author
  createAuthorizationHeader():HttpHeaders{
    let authHeaders: HttpHeaders = new HttpHeaders();
    return authHeaders.set(
      "Authorization","Bearer " + StorageService.getToken()
    );
  }
  login(loginRequest: any): Observable<any> {
    return this.http.post<[]>(BASIC_URL + "api/auth/login", loginRequest);
  }
}
