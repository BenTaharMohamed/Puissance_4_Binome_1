import { MenuSubmenuModule } from './menu-submenu.module';

describe('MenuSubmenuModule', () => {
  let menuSubmenuModule: MenuSubmenuModule;

  beforeEach(() => {
    menuSubmenuModule = new MenuSubmenuModule();
  });

  it('should create an instance', () => {
    expect(menuSubmenuModule).toBeTruthy();
  });
});
