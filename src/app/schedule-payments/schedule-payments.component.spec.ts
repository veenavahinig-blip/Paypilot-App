import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SchedulePaymentsComponent } from './schedule-payments.component';

describe('SchedulePaymentsComponent', () => {
  let component: SchedulePaymentsComponent;
  let fixture: ComponentFixture<SchedulePaymentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SchedulePaymentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SchedulePaymentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
