import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RateHotelComponent } from './rate-hotel.component';

describe('RateHotelComponent', () => {
  let component: RateHotelComponent;
  let fixture: ComponentFixture<RateHotelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RateHotelComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RateHotelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
