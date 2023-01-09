import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminEditCountryComponent } from './admin-edit-country.component';

describe('AdminEditCountryComponent', () => {
  let component: AdminEditCountryComponent;
  let fixture: ComponentFixture<AdminEditCountryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminEditCountryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminEditCountryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
