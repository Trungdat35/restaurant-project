import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomerRoutingModule } from './customer-routing.module';
import { DashboardComponent } from './customer-component/dashboard/dashboard.component';
import { DemoNgZorroAntdModule } from 'src/app/DemoNgZorroAntdModule';
import { FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ViewProductsByCategoryComponent } from './customer-component/view-products-by-category/view-products-by-category.component';
import { PostReservationComponent } from './customer-component/post-reservation/post-reservation.component';
import { GetAllReservationsComponent } from './customer-component/get-all-reservations/get-all-reservations.component';



@NgModule({
  declarations: [
    DashboardComponent,
    ViewProductsByCategoryComponent,
    PostReservationComponent,
    GetAllReservationsComponent,
  
  ],
  imports: [
    CommonModule,
    CustomerRoutingModule,
    DemoNgZorroAntdModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ]
})
export class CustomerModule { }
