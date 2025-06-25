import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportIssueDialogComponent } from './report-issue-dialog.component';

describe('ReportIssueDialogComponent', () => {
  let component: ReportIssueDialogComponent;
  let fixture: ComponentFixture<ReportIssueDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReportIssueDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ReportIssueDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
