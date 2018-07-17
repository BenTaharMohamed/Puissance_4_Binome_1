import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuComponent } from './menu.component';
import { MenuSubmenuModule } from '../menu-submenu/menu-submenu.module';

@NgModule({
  imports: [
    CommonModule,
    MenuSubmenuModule
  ],
  declarations: [MenuComponent],
  exports:[
    MenuComponent
  ]
})
export class MenuModule { }
