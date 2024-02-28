import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Route, Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { AuthService } from 'src/app/auth-service/auth-service/auth.service';
import { StorageService } from 'src/app/auth-service/storage-service/storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  loginForm: FormGroup;
  isSpinning: boolean;
  constructor(private service: AuthService, private fb: FormBuilder, private router: Router, private notfication: NzNotificationService) {
  }
  ngOnInit() {
    this.loginForm = this.fb.group({
      email: [null, Validators.required],
      password: [null, Validators.required],
    })
  }
  submitForm() {
    debugger
    this.service.login(this.loginForm.value).subscribe((res) => {
      console.log(res);
      if (res.userId != null) {
        const user = {
          id: res.userId,
          role: res.userRole
        }
        console.log(user);
        StorageService.saveToken(res.jwt);
        StorageService.saveUser(user);
        if (StorageService.isAdminLoggedIn()) {
          this.router.navigateByUrl("admin/dashboard")
        } else if (StorageService.isCustomerLoggedIn()) {
          this.router.navigateByUrl("customer/dashboard")
        }
      } else {
        console.log("Wrong credentials");
        this.notfication.success("Error", "Account and password are incorrect !", { nzDuration: 5000 });
      }
    })
  }
}
