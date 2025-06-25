import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EndTripDialogComponent } from './end-trip-dialog.component';

describe('EndTripDialogComponent', () => {
  let component: EndTripDialogComponent;
  let fixture: ComponentFixture<EndTripDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EndTripDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EndTripDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
