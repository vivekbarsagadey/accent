import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
    showChild: boolean;
    children?: Array<object>;
}
export const ROUTES: RouteInfo[] = [
    { path: 'dashboard', title: 'Dashboard',  icon: 'design_app', class: '', showChild: false, },
    { path: 'user-management', title: 'User Management',  icon: 'education_atom', class: '', showChild: false,
        children: [
            {path: 'user-management/user-profile-management', title: 'User Profile Management'},
            {path: 'user-management/current-opened-bills', title: 'Bulk upload user profile management'}
        ]
    },
    /*{ path: '/maps', title: 'Maps',  icon:'location_map-big', class: '', showChild: false },
    { path: '/notifications', title: 'Notifications',  icon:'ui-1_bell-53', class: '', showChild: false },

    { path: '/user-profile', title: 'User Profile',  icon:'users_single-02', class: '', showChild: false },
    { path: '/table-list', title: 'Table List',  icon:'design_bullet-list-67', class: '', showChild: false },
    { path: '/typography', title: 'Typography',  icon:'text_caps-small', class: '', showChild: false }*/
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  menuItems: any[];
  selectedItem;

  constructor(private router: Router) { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
  }
  isMobileMenu() {
      if ( window.innerWidth > 991) {
          return false;
      }
      return true;
  };

    listClick(event, newValue) {
        console.log(event);
        this.selectedItem = newValue;
        newValue.showChild = true;
        event.stopPropagation();
        /*  }*/
    }
}
