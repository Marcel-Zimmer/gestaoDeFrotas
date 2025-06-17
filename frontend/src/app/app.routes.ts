import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { VeiculosComponent } from './admin/pages/vehicles/vehicles.component';
import { adminGuard } from './auth/admin.guard'; // Importe o guarda
import { LayoutComponent as AdminLayoutComponent } from './admin/layout/layout.component'; // Renomeie para evitar conflito
import { DashboardComponent as AdminDashboardComponent } from './admin/pages/dashboard/dashboard.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },


  // ROTAS DO ADMIN
  {
    path: 'admin',
    component: AdminLayoutComponent,
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' }, // Redireciona /admin para /admin/dashboard
      { path: 'dashboard', component: AdminDashboardComponent },
      { path: 'veiculos', component: VeiculosComponent },     // Exemplo para o futuro
      //{ path: 'motoristas', component: MotoristasComponent }, // Exemplo para o futuro
    ]
  },

  // Futuramente, aqui teremos as rotas do motorista
  // { path: 'motorista', component: MotoristaLayoutComponent, canActivate: [motoristaGuard], children: [...] }
];