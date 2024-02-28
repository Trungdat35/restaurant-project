import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { AdminService } from '../../admin-service/admin.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { AuthService } from 'src/app/auth-service/auth-service/auth.service';
import { StorageService } from 'src/app/auth-service/storage-service/storage.service';

@Component({
  selector: 'app-post-product',
  templateUrl: './post-product.component.html',
  styleUrls: ['./post-product.component.scss']
})
export class PostProductComponent {
  categoryId: any = this.active.snapshot.params['categoryId'];
  loginForm: FormGroup;
  isSpinning = false;
  validateForm!: FormGroup;
  selectedFile: File | null;
  imagePreview: string | ArrayBuffer | null;
  constructor(private service: AdminService, private fb: FormBuilder, private router: Router, private message: NzMessageService, private active: ActivatedRoute) {
  }
  ngOnInit(): void {
    this.validateForm = this.fb.group({
      name: ["", Validators.required],
      price: ["", Validators.required],
      description: ["", Validators.required],
    })
  }
  submitForm() {
    debugger
    this.isSpinning = true;
    const formData: FormData = new FormData();
    formData.append("img", this.selectedFile);
    formData.append("name", this.validateForm.get("name").value);
    formData.append("price", this.validateForm.get("price").value);
    formData.append("description", this.validateForm.get("description").value);
    this.service.postProduct(this.categoryId, formData).subscribe((res) => {
      this.isSpinning = false;
      if (res.id != null) {
        this.message.success("Product Posted Successfilly !", { nzDuration: 5000 });
        this.router.navigateByUrl('/admin/dashboard');
      } else {
        this.message.error("Something went wrong !", { nzDuration: 5000 })
      }
    })
  }
  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    this.previewImage();
  }
  previewImage() {
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    }
    reader.readAsDataURL(this.selectedFile);
  }
}