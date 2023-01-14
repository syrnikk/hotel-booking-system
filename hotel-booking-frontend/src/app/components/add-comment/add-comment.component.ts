import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-add-comment',
  templateUrl: './add-comment.component.html',
  styleUrls: ['./add-comment.component.css']
})
export class AddCommentComponent {
  form: FormGroup;
  showValidationError: boolean = false;
  id: any;

  constructor(private fb: FormBuilder, private router: Router, private http: HttpClient, private route: ActivatedRoute) {
    this.form = this.fb.group({
      comment: [null]
    });
    this.id = this.route.snapshot.params['id'];
  }

  onSubmit() {
    const observer = {
      next: (data: any) => {
        console.log(data);
        this.router.navigate(['/reservation']);
      },
      error: (error: any) => { 
        console.log(error)
        this.showValidationError = true;
      }
    };

    this.addComment(this.form.value).subscribe(observer);
  }

  addComment(reservation: any) {
    return this.http.put<any>(`/api/booking/${this.id}`, reservation);
  }
}
