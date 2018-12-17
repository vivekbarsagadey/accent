import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseProfileManagementComponent } from './course-profile-management.component';

describe('CourseProfileManagementComponent', () => {
  let component: CourseProfileManagementComponent;
  let fixture: ComponentFixture<CourseProfileManagementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CourseProfileManagementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CourseProfileManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
