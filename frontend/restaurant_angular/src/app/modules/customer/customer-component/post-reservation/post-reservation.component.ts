import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { AdminService } from 'src/app/modules/admin/admin-service/admin.service';
import { CustomerService } from '../customer-service/customer.service';

@Component({
  selector: 'app-post-reservation',
  templateUrl: './post-reservation.component.html',
  styleUrls: ['./post-reservation.component.scss']
})
export class PostReservationComponent {
  isSpinning = false;
  validateForm: FormGroup;
  TableType:string[]=[
    "Standard Table",
    "Outdoor Table",
    "Custom Table"
  ]
  constructor(private service: CustomerService, private fb: FormBuilder, private router: Router, private message: NzMessageService) {
  }
  ngOnInit(): void {
    this.validateForm = this.fb.group({
      tableType: ["", Validators.required],
      dateTime: ["", Validators.required],
      description: ["", Validators.required],
    })
  }
  postReservation() {
    debugger
    this.service.postReservation(this.validateForm.value).subscribe((res) => {
      if (res.id != null) {
        this.message.success("Reservation Posted Successfilly !", { nzDuration: 5000 });
        this.router.navigateByUrl('/customer/dashboard');
      } else {
        this.message.error("Something went wrong !", { nzDuration: 5000 })
      }
    })
  }
}
