import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminRoomTypeComponent } from './admin-room-type.component';

describe('AdminRoomTypeComponent', () => {
  let component: AdminRoomTypeComponent;
  let fixture: ComponentFixture<AdminRoomTypeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminRoomTypeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminRoomTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
