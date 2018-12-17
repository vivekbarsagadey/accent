import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {RouterModule} from '@angular/router';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {AppRoutingModule} from './app.routing';
import {ComponentsModule} from './components/components.module';
import {AppComponent} from './app.component';
import {AdminLayoutComponent} from './layouts/admin-layout/admin-layout.component';
import {LoginComponent} from './login/login.component';
import { UserManagementComponent } from './user-management/user-management.component';
import { UserSettingsComponent } from './user-management/user-settings/user-settings.component';
import { UserRecommenderComponent } from './user-management/user-recommender/user-recommender.component';
import { UserLearningLifeCycleComponent } from './user-management/user-learning-life-cycle/user-learning-life-cycle.component';
import {UserProfileManagementComponent} from './user-management/user-profile-management/user-profile-management.component';
import { BulkUploadUserProfileComponent } from './user-management/bulk-upload-user-profile/bulk-upload-user-profile.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import { WordComponent } from './word/word.component';
import { WordManagementComponent } from './word/word-management/word-management.component';
import { BulkWordUploadComponent } from './word/bulk-word-upload/bulk-word-upload.component';
import { WordCategoryComponent } from './word-category/word-category.component';
import { WordCategoryManagementComponent } from './word-category/word-category-management/word-category-management.component';
import { CoursesComponent } from './courses/courses.component';
import { CourseProfileManagementComponent } from './courses/course-profile-management/course-profile-management.component';
import { CourseInfoComponent } from './courses/course-info/course-info.component';
import { UserPracticeSessionComponent } from './user-practice-session/user-practice-session.component';
import { AdminComponent } from './admin/admin.component';
import { UserManagementSystemComponent } from './admin/user-management-system/user-management-system.component';
import { UserProfileComponent } from './admin/user-profile/user-profile.component';
import { CourseManagementComponent } from './admin/course-management/course-management.component';
import { UserActivitiesComponent } from './user-activities/user-activities.component';
import { ConnectAccentComponent } from './connect-accent/connect-accent.component';
import { LoginManagementSystemComponent } from './login-management-system/login-management-system.component';
import { MembershipComponent } from './membership/membership.component';
import { BillingComponent } from './billing/billing.component';
import { PaymentIntegrationComponent } from './payment-integration/payment-integration.component';
import { SecurityComponent } from './security/security.component';
import { AdminWordManagementComponent } from './admin/admin-word-management/admin-word-management.component';
import { AdminReportsComponent } from './admin/admin-reports/admin-reports.component';
import {ReportsComponent} from './reports/reports.component';

@NgModule({
    imports: [
        BrowserAnimationsModule,
        FormsModule,
        HttpModule,
        ComponentsModule,
        RouterModule,
        AppRoutingModule,
        NgbModule.forRoot()
    ],
    declarations: [
        AppComponent,
        AdminLayoutComponent,
        LoginComponent,
        DashboardComponent,
        UserManagementComponent,
        UserProfileManagementComponent,
        UserSettingsComponent,
        UserRecommenderComponent,
        UserLearningLifeCycleComponent,
        BulkUploadUserProfileComponent,
        WordComponent,
        WordManagementComponent,
        BulkWordUploadComponent,
        WordCategoryComponent,
        WordCategoryManagementComponent,
        CoursesComponent,
        CourseProfileManagementComponent,
        CourseInfoComponent,
        UserPracticeSessionComponent,
        AdminComponent,
        UserManagementSystemComponent,
        UserProfileComponent,
        CourseManagementComponent,
        ReportsComponent,
        UserActivitiesComponent,
        ConnectAccentComponent,
        LoginManagementSystemComponent,
        MembershipComponent,
        BillingComponent,
        PaymentIntegrationComponent,
        SecurityComponent,
        AdminWordManagementComponent,
        AdminReportsComponent,
        ReportsComponent
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
