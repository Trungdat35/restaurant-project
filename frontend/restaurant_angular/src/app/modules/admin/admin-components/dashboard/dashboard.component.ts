import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { AdminService } from '../../admin-service/admin.service';
import { NzButtonSize } from 'ng-zorro-antd/button';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
  categories: any = [];
  validateForm!: FormGroup;
  size: NzButtonSize = 'large';
  isSpinning: boolean;
  constructor(private service: AdminService, private fb: FormBuilder, private message: NzMessageService) { }

  ngOnInit() {
    this.validateForm = this.fb.group({
      title: [null, [Validators.required]]
    })
    this.getAllCategories();
  }
  getAllCategories() {
    this.categories = [];

    this.service.getAllCategory().subscribe((res) => {
      console.log(res);
      res.forEach(element => {
        element.processedImg = 'data:image/jpeg;base64,' + element.returnedImg;
        this.categories.push(element);
      });
    })
  }
  submitForm() {
    this.isSpinning = true;
    this.categories = [];
    this.service.getAllCategoryByTitle(this.validateForm.get(['title'])!.value).subscribe((res) => {
      res.forEach(element => {
        element.processedImg = 'data:image/jpeg;base64,' + element.returnedImg;
        this.categories.push(element);
        this.isSpinning= false;
      });
    })
  }
}
