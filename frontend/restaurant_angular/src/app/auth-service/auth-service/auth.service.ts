import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IAuthService } from '../i-auth-service';
const BASIC_URL = "http://localhost:8088/"
@Injectable({
  providedIn: 'root' // toàn ứng dụng
})
export class AuthService implements IAuthService{

  constructor(private http: HttpClient) { }

  signup(signupRequest: any): Observable<any> {
    return this.http.post<[]>(BASIC_URL + "api/auth/signup", signupRequest);
  }
  login(loginRequest: any): Observable<any> {
    return this.http.post<[]>(BASIC_URL + "api/auth/login", loginRequest);
  }
}
