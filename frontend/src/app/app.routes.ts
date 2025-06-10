import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component'; // Verifique se este import está correto

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent }
];