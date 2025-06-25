import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RefuelingComponent } from './refueling.component';

describe('RefuelingComponent', () => {
  let component: RefuelingComponent;
  let fixture: ComponentFixture<RefuelingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RefuelingComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RefuelingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
