import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuSubmenuComponent } from './menu-submenu.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [MenuSubmenuComponent],
  exports:[
    MenuSubmenuComponent
  ]
})
export class MenuSubmenuModule { }
