import { Component, OnInit, Input} from '@angular/core';

@Component({
  selector: 'app-menu-submenu',
  templateUrl: './menu-submenu.component.html',
  styleUrls: ['./menu-submenu.component.scss']
})
export class MenuSubmenuComponent implements OnInit {
  @Input() menuText:string;
  constructor() { }

  ngOnInit() {
  }

}
