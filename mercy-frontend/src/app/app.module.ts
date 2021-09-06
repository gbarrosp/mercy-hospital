import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { MatDividerModule } from '@angular/material/divider';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorIntl, MatPaginatorModule } from '@angular/material/paginator';
import { MatSelectModule } from '@angular/material/select';
import { MatSnackBarModule, MAT_SNACK_BAR_DEFAULT_OPTIONS } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgSelectModule } from '@ng-select/ng-select';
import { NgxInputLoaderModule } from 'ngx-input-loader';
import { IConfig, NgxMaskModule } from 'ngx-mask';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { PatientDialogComponent } from './components/patient-dialog/patient-dialog.component';
import { PatientsComponent } from './components/patients/patients.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { SystemLayoutComponent } from './components/system-layout/system-layout.component';
import { AuthInterceptor } from './services/auth/auth.interceptor';
import { AuthService } from './services/views/auth.service';
import { DoctorService } from './services/views/doctor.service';
import { PatientService } from './services/views/patient.service';
import { ZipCodeService } from './services/views/zipCode.service';
import { getPtPaginatorIntl } from './util/pt-paginator-intl';
import { LandingRoutingModule } from './views/landing/landing-routing.module';
import { LandingComponent } from './views/landing/landing.component';

export const options: Partial<IConfig> | (() => Partial<IConfig>) = null;

@NgModule({
  declarations: [
    AppComponent,
    LandingComponent,
    SystemLayoutComponent,
    PatientsComponent,
    HeaderComponent,
    PatientDialogComponent,
    SignUpComponent
  ],
  imports: [
    BrowserModule,
    LandingRoutingModule,
    AppRoutingModule,
    FlexLayoutModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDividerModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    MatTableModule,
    MatPaginatorModule,
    MatCardModule,
    MatIconModule,
    MatSelectModule,
    MatToolbarModule,
    MatDialogModule,
    NgSelectModule,
    MatSnackBarModule,
    MatExpansionModule,
    HttpClientModule,
    NgxInputLoaderModule,
    NgxMaskModule.forRoot(),
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    { provide: MatPaginatorIntl, useValue: getPtPaginatorIntl()},
    { provide: MAT_SNACK_BAR_DEFAULT_OPTIONS, useValue: {duration: 5000}},
    AuthService,
    PatientService,
    DoctorService,
    ZipCodeService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
