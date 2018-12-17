import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WordCategoryManagementComponent } from './word-category-management.component';

describe('WordCategoryManagementComponent', () => {
  let component: WordCategoryManagementComponent;
  let fixture: ComponentFixture<WordCategoryManagementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WordCategoryManagementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WordCategoryManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
