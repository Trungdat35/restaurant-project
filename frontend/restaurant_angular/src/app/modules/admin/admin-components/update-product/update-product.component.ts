import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { AdminService } from '../../admin-service/admin.service';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.scss']
})
export class UpdateProductComponent {
  productId: any = this.active.snapshot.params['productId'];
  isSpinning = false;
  imageChanged = false;
  validateForm!: FormGroup;
  selectedFile: any;
  imagePreview: string | ArrayBuffer | null = null;
  existingImg: string | null = null;
  constructor(private service: AdminService, private fb: FormBuilder, private router: Router, private message: NzMessageService, private active: ActivatedRoute) {
  }
  ngOnInit(): void {
    this.validateForm = this.fb.group({
      name: [null, Validators.required],
      price: [null, Validators.required],
      description: [null, Validators.required],
    })
    this.getProductById();
  }
  getProductById() {
    this.service.getProductById(this.productId).subscribe((res) => {
      console.log(res);
      const productDto = res;
      this.existingImg = 'data:image/jpeg;base64,' + productDto.returnedImg;
      this.validateForm.patchValue(productDto);
    })
  }
  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    this.previewImage();
    this.imageChanged = true;
    this.existingImg = null;
  }
  previewImage() {
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    }
    reader.readAsDataURL(this.selectedFile);
  }
  updateProduct(): void {
    debugger
    this.isSpinning = true;
    const formData: FormData = new FormData();
    if (this.imageChanged && this.selectedFile) {
      formData.append("img", this.selectedFile);
    }
    formData.append("name", this.validateForm.get("name").value);
    formData.append("price", this.validateForm.get("price").value);
    formData.append("description", this.validateForm.get("description").value);
    console.log(formData)
    this.service.updateProduct(this.productId, formData).subscribe((res) => {
      this.isSpinning = false;
      debugger
      if (res.id != null) {
        this.message.success("Product update Successfilly !", { nzDuration: 5000 });
        this.router.navigateByUrl('/admin/dashboard');
      } else {
        this.message.error("Something went wrong !", { nzDuration: 5000 })
      }
    })
  }
}
