import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAddCityComponent } from './admin-add-city.component';

describe('AdminAddCityComponent', () => {
  let component: AdminAddCityComponent;
  let fixture: ComponentFixture<AdminAddCityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminAddCityComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminAddCityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
