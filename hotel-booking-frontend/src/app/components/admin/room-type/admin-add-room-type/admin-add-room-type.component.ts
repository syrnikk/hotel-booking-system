import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-admin-add-room-type',
  templateUrl: './admin-add-room-type.component.html',
  styleUrls: ['./admin-add-room-type.component.css']
})
export class AdminAddRoomTypeComponent {
  isAddMode = true;
  id: any;
  form: FormGroup;
  showValidationError: boolean = false;

  constructor(private fb: FormBuilder, private router: Router, private http: HttpClient, private route: ActivatedRoute) {
    this.form = this.fb.group({
      type: [null, Validators.required],
      amountOfPeople: [null, Validators.required],
      price: [null, Validators.required],
      description: [null, Validators.required],
    });

    this.id = this.route.snapshot.params['id'];
    this.isAddMode = !this.id;

    if (!this.isAddMode) {
      this.findRoomTypeById(this.id).subscribe(roomType => this.form.patchValue(roomType));
    }
  }

  onSubmit() {
    const observer = {
      next: (data: any) => {
        console.log(data);
        this.router.navigate(['/admin/room-type']);
      },
      error: (error: any) => { 
        console.log(error)
        this.showValidationError = true;
      }
    };

    if(this.isAddMode) {
      this.addRoomType(this.form.value).subscribe(observer);
    } else {
      this.editRoomType(this.form.value).subscribe(observer);
    }
    }

  addRoomType(roomType: any) {
    return this.http.post<any>('/api/room-type', roomType);
  }

  editRoomType(roomType: any) {
    return this.http.put<any>(`/api/room-type/${this.id}`, roomType);
  }

  findRoomTypeById(id: number) {
    return this.http.get<any>(`/api/room-type/${id}`);
  }
}
