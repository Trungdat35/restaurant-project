import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { AdminService } from '../../admin-service/admin.service';
import { ActivatedRoute } from '@angular/router';
import { NzButtonSize } from 'ng-zorro-antd/button';

@Component({
  selector: 'app-view-products',
  templateUrl: './view-products.component.html',
  styleUrls: ['./view-products.component.scss']
})
export class ViewProductsComponent {
  categoryId: any = this.active.snapshot.params['categoryId'];
  Products: any = [];
  isSpinning: boolean;
  categories: any = [];
  validateForm: FormGroup;
  size: NzButtonSize = 'large';
  constructor(private service: AdminService, private fb: FormBuilder, private active: ActivatedRoute, private message: NzMessageService) { }

  ngOnInit() {
    this.validateForm = this.fb.group({
      title: [null, [Validators.required]]
    })
    this.getProductByCategories();
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
  getProductByCategories() {
    this.Products = [];
    this.service.getProductByCategory(this.categoryId).subscribe((res) => {
      res.forEach(element => {
        element.processedImg = 'data:image/jpeg;base64,' + element.returnedImg;
        this.Products.push(element);
      });
    })
  }

  deleteProduct(productid: number) {
    this.service.deleteProduct(productid).subscribe((res) => {
      console.log(res);
      if (res == null) {
        this.getProductByCategories();
        this.message.success(`Product deleted successfully !`, { nzDuration: 5000 });
      } else {
        this.message.error(`Something went wrong !`, { nzDuration: 5000 });
      }
    })
  }
}
