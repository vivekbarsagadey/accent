import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConnectAccentComponent } from './connect-accent.component';

describe('ConnectAccentComponent', () => {
  let component: ConnectAccentComponent;
  let fixture: ComponentFixture<ConnectAccentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConnectAccentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConnectAccentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
