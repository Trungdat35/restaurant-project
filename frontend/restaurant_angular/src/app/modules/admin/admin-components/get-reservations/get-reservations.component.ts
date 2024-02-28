import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { CustomerService } from 'src/app/modules/customer/customer-component/customer-service/customer.service';
import { AdminService } from '../../admin-service/admin.service';

@Component({
  selector: 'app-get-reservations',
  templateUrl: './get-reservations.component.html',
  styleUrls: ['./get-reservations.component.scss']
})
export class GetReservationsComponent {
  isSpinning: boolean = false;
  reservations: any;
  constructor(private service: AdminService, private fb: FormBuilder, private active: ActivatedRoute, private message: NzMessageService) { }

  ngOnInit() {
    this.getReserationByUser();
  }
  getReserationByUser() {
    this.service.getReservations().subscribe((res) => {
      console.log(res);
      this.reservations = res;
    })
  }
  changeReservationStatus(reservationId: number, status: string) {
    this.service.changeReservationStatus(reservationId, status).subscribe((res) => {
      console.log(res);
      if (res.id != null) {
        this.getReserationByUser();
        this.message.success("Reservation status changed Successfully !", { nzDuration: 5000 });
      } else {
        this.message.error("Something went wrong !", { nzDuration: 5000 })
      }
    })
  }
}
