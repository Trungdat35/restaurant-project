import { Component } from '@angular/core';
import { StorageService } from './auth-service/storage-service/storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'restaurant_angular';
  isAdminLoggedIn: boolean = StorageService.isAdminLoggedIn();
  isCustomerLoggedIn: boolean = StorageService.isCustomerLoggedIn();
  constructor(private router: Router) { }
  ngOnInit() {
    this.router.events.subscribe(event => {
      this.isAdminLoggedIn = StorageService.isAdminLoggedIn();
      this.isCustomerLoggedIn = StorageService.isCustomerLoggedIn();
    })
  }
  logout(){
    StorageService.signout();
    this.router.navigateByUrl("/login")
  }
}
