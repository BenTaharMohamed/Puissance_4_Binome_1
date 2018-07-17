import { Component, OnInit } from '@angular/core';
@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {
  menu_visible=false;
  menu_button="menu";
  menu_text:string="person";
  activeClass:boolean=false;
  menu_open(){
    this.menu_visible=!this.menu_visible;
    this.menu_visible?this.menu_button="done":this.menu_button="menu";
  }
  menu_output(output:string){
    // this.activeClass=!this.activeClass;
    this.menu_text=output;
  }
  constructor() { }

  ngOnInit() {
  }

}
