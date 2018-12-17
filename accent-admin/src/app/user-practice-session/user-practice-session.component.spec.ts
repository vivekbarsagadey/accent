import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserPracticeSessionComponent } from './user-practice-session.component';

describe('UserPracticeSessionComponent', () => {
  let component: UserPracticeSessionComponent;
  let fixture: ComponentFixture<UserPracticeSessionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserPracticeSessionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserPracticeSessionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
