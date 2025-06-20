import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgendamentoComponentComponent } from './agendamento-component.component';

describe('AgendamentoComponentComponent', () => {
  let component: AgendamentoComponentComponent;
  let fixture: ComponentFixture<AgendamentoComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AgendamentoComponentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AgendamentoComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
