import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { IAuthService } from 'src/app/auth-service/i-auth-service';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit{
  isSpinning: boolean;
  validateForm: FormGroup;

  confirmationValidator = (control: FormControl): { [s: string]: boolean } => {
    debugger
    if (!control.value) {
      return { require: true }
    } else if (control.value !== this.validateForm.controls['password'].value) {
      return { confirm: true, error: true }
    }
    return {};
  }
  constructor(@Inject(String) private service: IAuthService, private router: Router,private fb: FormBuilder, private notification: NzNotificationService) {
  }
  ngOnInit() {
    this.validateForm = this.fb.group({
      email: ["", Validators.required],
      password: ["", Validators.required],
      checkPassword: ["", [Validators.required, this.confirmationValidator]],
      name: ["", Validators.required],
    })
  }

  register() {
    debugger
    const { checkPassword, email, name, password } = this.validateForm.value;
    if (!checkPassword || !email || !name || !password) {
      this.notification.error("Error", "Please fill in all fields!", { nzDuration: 5000 });
      return;
    } else {
      this.service.signup(this.validateForm.value)
        .subscribe((res) => {
          console.log(res);
          if (res.id == null) {
            this.validateForm.reset();
            this.notification.success("Success", "You're registered successfully !", { nzDuration: 5000 });
            this.router.navigateByUrl('/login');
          } else {
            this.notification.error("Error", "Something went wrong !", { nzDuration: 5000 });
          }
        });
    }

  }
}
