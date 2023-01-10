import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAddRoomTypeComponent } from './admin-add-room-type.component';

describe('AdminAddRoomTypeComponent', () => {
  let component: AdminAddRoomTypeComponent;
  let fixture: ComponentFixture<AdminAddRoomTypeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminAddRoomTypeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminAddRoomTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
