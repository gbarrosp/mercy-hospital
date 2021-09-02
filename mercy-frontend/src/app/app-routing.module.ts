import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PatientsComponent } from './components/patients/patients.component';
import { SystemLayoutComponent } from './components/system-layout/system-layout.component';
import { AuthGuard } from './services/auth/auth.guard';
import { Views } from './util/views.enum';


const routes: Routes = [
  {
    path: '',
    component: SystemLayoutComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: Views.patients.url,
        component: PatientsComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  providers: [AuthGuard],
  exports: [RouterModule]
})
export class AppRoutingModule { }
