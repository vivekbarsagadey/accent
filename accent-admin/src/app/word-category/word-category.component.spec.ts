import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WordCategoryComponent } from './word-category.component';

describe('WordCategoryComponent', () => {
  let component: WordCategoryComponent;
  let fixture: ComponentFixture<WordCategoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WordCategoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WordCategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
