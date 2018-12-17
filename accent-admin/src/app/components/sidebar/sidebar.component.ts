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
            {path: 'user-management/user-profile', title: 'User Profile Management'},
            {path: 'user-management/bulk-user-profile', title: 'Bulk upload user profile management'},
            {path: 'user-management/user-settings', title: 'User Settings'},
            {path: 'user-management/user-recommender', title: 'User Recommender'},
            {path: 'user-management/user-learning-life-cycle', title: 'User Learning Life Cycle'},
        ]
    },
    { path: 'word', title: 'Word',  icon: 'education_atom', class: '', showChild: false,
        children: [
            {path: 'word/word-manage', title: 'Word Management'},
            {path: 'word/bulk-word-upload', title: 'Bulk Word Upload'},
        ]
    },
    { path: 'word-category', title: 'Word Category',  icon: 'education_atom', class: '', showChild: false,
        children: [
            {path: 'word-category/word-category-manage', title: 'Word Category Management'}
        ]
    },
    { path: 'courses', title: 'Courses',  icon: 'education_atom', class: '', showChild: false,
        children: [
            {path: 'courses/course-info', title: 'Course Information'},
            {path: 'courses/course-profile-management', title: 'Course Profile Management'}
        ]
    },
    { path: 'admin', title: 'Admin',  icon: 'education_atom', class: '', showChild: false,
        children: [
            {path: 'admin/user-management-system', title: 'User Management System'},
            {path: 'admin/user-profile', title: 'User Profile'},
            {path: 'admin/word-management', title: 'Word Management'},
            {path: 'admin/course-management', title: 'Course Management'},
            {path: 'admin/reports', title: 'Reports'}
        ]
    },
    { path: 'connect-accent', title: 'Connect to Accent System',  icon: 'design_app', class: '', showChild: false, },
    { path: 'login-management', title: 'Login Management System',  icon: 'design_app', class: '', showChild: false, },
    { path: 'memebership', title: 'Membership',  icon: 'design_app', class: '', showChild: false, },
    { path: 'billing', title: 'Billing',  icon: 'design_app', class: '', showChild: false, },
    { path: 'payment-integration', title: 'Payment Integration',  icon: 'design_app', class: '', showChild: false, },
    { path: 'security', title: 'Security',  icon: 'design_app', class: '', showChild: false, },
    { path: 'reports', title: 'Reports',  icon: 'design_app', class: '', showChild: false, }

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
