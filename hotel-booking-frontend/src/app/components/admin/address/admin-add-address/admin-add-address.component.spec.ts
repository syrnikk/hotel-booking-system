import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAddAddressComponent } from './admin-add-address.component';

describe('AdminAddAddressComponent', () => {
  let component: AdminAddAddressComponent;
  let fixture: ComponentFixture<AdminAddAddressComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminAddAddressComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminAddAddressComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
