import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';
import {DashboardComponent} from './dashboard/dashboard.component';
import {UserManagementComponent} from './user-management/user-management.component';
import {UserProfileManagementComponent} from './user-management/user-profile-management/user-profile-management.component';
import {BulkUploadUserProfileComponent} from './user-management/bulk-upload-user-profile/bulk-upload-user-profile.component';
import {UserSettingsComponent} from './user-management/user-settings/user-settings.component';
import {UserRecommenderComponent} from './user-management/user-recommender/user-recommender.component';
import {UserLearningLifeCycleComponent} from './user-management/user-learning-life-cycle/user-learning-life-cycle.component';
import {WordComponent} from './word/word.component';
import {WordManagementComponent} from './word/word-management/word-management.component';
import {BulkWordUploadComponent} from './word/bulk-word-upload/bulk-word-upload.component';
import {WordCategoryComponent} from './word-category/word-category.component';
import {WordCategoryManagementComponent} from './word-category/word-category-management/word-category-management.component';
import {CoursesComponent} from './courses/courses.component';
import {CourseInfoComponent} from './courses/course-info/course-info.component';
import {CourseProfileManagementComponent} from './courses/course-profile-management/course-profile-management.component';
import {UserPracticeSessionComponent} from './user-practice-session/user-practice-session.component';
import {AdminComponent} from './admin/admin.component';
import {UserManagementSystemComponent} from './admin/user-management-system/user-management-system.component';
import {UserProfileComponent} from './admin/user-profile/user-profile.component';
import {AdminWordManagementComponent} from './admin/admin-word-management/admin-word-management.component';
import {CourseManagementComponent} from './admin/course-management/course-management.component';
import {ConnectAccentComponent} from './connect-accent/connect-accent.component';
import {LoginManagementSystemComponent} from './login-management-system/login-management-system.component';
import {MembershipComponent} from './membership/membership.component';
import {BillingComponent} from './billing/billing.component';
import {PaymentIntegrationComponent} from './payment-integration/payment-integration.component';
import {SecurityComponent} from './security/security.component';
import {ReportsComponent} from './reports/reports.component';
import {AdminReportsComponent} from './admin/admin-reports/admin-reports.component';


const routes: Routes = [
    {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
    }, {
        path: 'dashboard',
        component: DashboardComponent,
    },
    {
        path: 'user-management',
        component: UserManagementComponent,
        children: [
            {path: '', component: UserManagementComponent},
            {path: 'user-profile', component: UserProfileManagementComponent},
            {path: 'bulk-user-profile', component: BulkUploadUserProfileComponent},
            {path: 'user-settings', component: UserSettingsComponent},
            {path: 'user-recommender', component: UserRecommenderComponent},
            {path: 'user-learning-life-cycle', component: UserLearningLifeCycleComponent}

        ]
    },
    {
        path: 'word',
        component: WordComponent,
        children: [
            {path: '', component: WordComponent},
            {path: 'word-manage', component: WordManagementComponent},
            {path: 'bulk-word-upload', component: BulkWordUploadComponent}
        ]
    },
    {
        path: 'word-category',
        component: WordCategoryComponent,
        children: [
            {path: '', component: WordCategoryComponent},
            {path: 'word-category-manage', component: WordCategoryManagementComponent}
        ]
    },
    {
        path: 'courses',
        component: CoursesComponent,
        children: [
            {path: '', component: CoursesComponent},
            {path: 'course-info', component: CourseInfoComponent},
            {path: 'course-profile-management', component: CourseProfileManagementComponent}
        ]
    },
    {
        path: 'user-practice-session',
        component: UserPracticeSessionComponent,
    },
    {
        path: 'courses',
        component: CoursesComponent,
        children: [
            {path: '', component: CoursesComponent},
            {path: 'course-info', component: CourseInfoComponent},
            {path: 'course-profile-management', component: CourseProfileManagementComponent}
        ]
    },
    {
        path: 'admin',
        component: AdminComponent,
        children: [
            {path: '', component: AdminComponent},
            {path: 'user-management-system', component: UserManagementSystemComponent},
            {path: 'user-profile', component: UserProfileComponent},
            {path: 'word-management', component: AdminWordManagementComponent},
            {path: 'course-management', component: CourseManagementComponent},
            {path: 'reports', component: AdminReportsComponent}
        ]
    },
    {
        path: 'connect-accent',
        component: ConnectAccentComponent,
    },
    {
        path: 'login-management',
        component: LoginManagementSystemComponent,
    },
    {
        path: 'memebership',
        component: MembershipComponent,
    },
    {
        path: 'billing',
        component: BillingComponent,
    },
    {
        path: 'payment-integration',
        component: PaymentIntegrationComponent,
    },
    {
        path: 'security',
        component: SecurityComponent,
    },
    {
        path: 'reports',
        component: ReportsComponent,
    }
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
      RouterModule
  ],
})
export class AppRoutingModule { }
