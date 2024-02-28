import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NzButtonSize } from 'ng-zorro-antd/button';
import { NzMessageService } from 'ng-zorro-antd/message';
import { AdminService } from 'src/app/modules/admin/admin-service/admin.service';
import { CustomerService } from '../customer-service/customer.service';

@Component({
  selector: 'app-view-products-by-category',
  templateUrl: './view-products-by-category.component.html',
  styleUrls: ['./view-products-by-category.component.scss']
})
export class ViewProductsByCategoryComponent {
  categoryId: any = this.active.snapshot.params['categoryId'];
  Products: any = [];
  isSpinning: boolean;
  categories: any = [];
  validateForm: FormGroup;
  size: NzButtonSize = 'large';
  constructor(private service: CustomerService, private fb: FormBuilder, private active: ActivatedRoute, private message: NzMessageService) { }

  ngOnInit() {
    this.validateForm = this.fb.group({
      title: [null, [Validators.required]]
    })
    this.getProductByCategories();
  }
  getProductByCategories() {
    this.Products = [];
    this.service.getProductByCategory(this.categoryId).subscribe((res) => {
      res.forEach(element => {
        element.processedImg = 'data:image/jpeg;base64,' + element.returnedImg;
        this.Products.push(element);
      });
    })
  }
  submitForm() {
    this.isSpinning = true;
    this.Products = [];
    this.service.getProductByCategoryAndTitle(this.categoryId, this.validateForm.get(['title'])!.value).subscribe((res) => {
      console.log(res);
      res.forEach(element => {
        element.processedImg = 'data:image/jpeg;base64,' + element.returnedImg;
        this.Products.push(element);
        this.isSpinning = false;
      });
    })
  }
 
}
