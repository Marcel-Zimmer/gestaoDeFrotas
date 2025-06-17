import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VeiculoDialogComponent } from './vehicle-dialog.component';

describe('VeiculoDialogComponent', () => {
  let component: VeiculoDialogComponent;
  let fixture: ComponentFixture<VeiculoDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VeiculoDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VeiculoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
