import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { AdminService } from 'src/app/modules/admin/admin-service/admin.service';
import { CustomerService } from '../customer-service/customer.service';

@Component({
  selector: 'app-get-all-reservations',
  templateUrl: './get-all-reservations.component.html',
  styleUrls: ['./get-all-reservations.component.scss']
})
export class GetAllReservationsComponent {
  isSpinning:boolean=false;
  reservations: any;
  constructor(private service: CustomerService, private fb: FormBuilder, private active: ActivatedRoute, private message: NzMessageService) { }

  ngOnInit() {
    this.getReserationByUser();
  }
  getReserationByUser(){
    this.service.getReservationByUser().subscribe((res)=>{
      console.log(res);
      this.reservations=res;
    })
  }
}
