import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BulkUploadUserProfileComponent } from './bulk-upload-user-profile.component';

describe('BulkUploadUserProfileComponent', () => {
  let component: BulkUploadUserProfileComponent;
  let fixture: ComponentFixture<BulkUploadUserProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BulkUploadUserProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BulkUploadUserProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
