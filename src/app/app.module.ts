import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuModule } from './menu/menu.module';
import { MenuSubmenuModule } from './menu-submenu/menu-submenu.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MenuModule,
    MenuSubmenuModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
