import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginManagementSystemComponent } from './login-management-system.component';

describe('LoginManagementSystemComponent', () => {
  let component: LoginManagementSystemComponent;
  let fixture: ComponentFixture<LoginManagementSystemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginManagementSystemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginManagementSystemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
