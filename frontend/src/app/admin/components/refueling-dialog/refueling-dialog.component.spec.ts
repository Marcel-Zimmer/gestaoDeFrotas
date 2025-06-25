import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RefuelingDialogComponent } from './refueling-dialog.component';

describe('RefuelingDialogComponent', () => {
  let component: RefuelingDialogComponent;
  let fixture: ComponentFixture<RefuelingDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RefuelingDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RefuelingDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
