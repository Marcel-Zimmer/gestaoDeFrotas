import { TestBed } from '@angular/core/testing';

import { DriversService } from './admin.service';

describe('DriversService', () => {
  let service: DriversService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DriversService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
