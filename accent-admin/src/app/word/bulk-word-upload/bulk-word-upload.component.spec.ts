import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BulkWordUploadComponent } from './bulk-word-upload.component';

describe('BulkWordUploadComponent', () => {
  let component: BulkWordUploadComponent;
  let fixture: ComponentFixture<BulkWordUploadComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BulkWordUploadComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BulkWordUploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
