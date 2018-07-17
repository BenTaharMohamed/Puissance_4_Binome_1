import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuSubmenuComponent } from './menu-submenu.component';

describe('MenuSubmenuComponent', () => {
  let component: MenuSubmenuComponent;
  let fixture: ComponentFixture<MenuSubmenuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MenuSubmenuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MenuSubmenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
