import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MaintenceDialogComponent } from './maintence-dialog.component';

describe('MaintenceDialogComponent', () => {
  let component: MaintenceDialogComponent;
  let fixture: ComponentFixture<MaintenceDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MaintenceDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MaintenceDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
