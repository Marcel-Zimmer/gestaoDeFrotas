import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StartTripDialogComponent } from './start-trip-dialog.component';

describe('StartTripDialogComponent', () => {
  let component: StartTripDialogComponent;
  let fixture: ComponentFixture<StartTripDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StartTripDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(StartTripDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
