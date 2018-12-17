import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserLearningLifeCycleComponent } from './user-learning-life-cycle.component';

describe('UserLearningLifeCycleComponent', () => {
  let component: UserLearningLifeCycleComponent;
  let fixture: ComponentFixture<UserLearningLifeCycleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserLearningLifeCycleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserLearningLifeCycleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
