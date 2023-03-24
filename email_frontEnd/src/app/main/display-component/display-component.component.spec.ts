import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayComponentComponent } from './display-component.component';

describe('DisplayComponentComponent', () => {
  let component: DisplayComponentComponent;
  let fixture: ComponentFixture<DisplayComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayComponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisplayComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
