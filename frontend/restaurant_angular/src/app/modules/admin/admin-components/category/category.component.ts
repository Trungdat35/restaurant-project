import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NzButtonSize } from 'ng-zorro-antd/button';
import { NzMessageService } from 'ng-zorro-antd/message';
import { AdminService } from '../../admin-service/admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss']
})
export class CategoryComponent {
  categories: any = [];
  validateForm!: FormGroup;
  size: NzButtonSize = 'large';
  isSpinning: boolean;
  constructor(private service: AdminService,private router: Router, private fb: FormBuilder, private message: NzMessageService) { }

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
  deleteCategory(categoryId: number) {
    this.service.deleteCategory(categoryId).subscribe((res) => {
      console.log(res);
      if (res == null) {
        this.message.success(`Category deleted successfully !`, { nzDuration: 5000 });
        this.router.navigateByUrl('/admin/dashboard');
      } else {
        this.message.error(`Something went wrong !`, { nzDuration: 5000 });
      }
    })
  }
}
