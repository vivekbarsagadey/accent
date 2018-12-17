import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminWordManagementComponent } from './admin-word-management.component';

describe('AdminWordManagementComponent', () => {
  let component: AdminWordManagementComponent;
  let fixture: ComponentFixture<AdminWordManagementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminWordManagementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminWordManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
