import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { VeiculosComponent } from './admin/pages/vehicles/vehicles.component';
import {DriversComponent} from './admin/pages/drivers/drivers.component';
import { adminGuard } from './auth/admin.guard'; // Importe o guarda
import { LayoutComponent as AdminLayoutComponent } from './admin/layout/layout.component'; // Renomeie para evitar conflito
import { DashboardComponent as AdminDashboardComponent } from './admin/pages/dashboard/dashboard.component';
import { AdminComponent } from './admin/pages/admin/admin.component';
import { ConfigComponent } from './admin/pages/config/config.component';

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
      { path: 'motoristas', component: DriversComponent }, // Exemplo para o futuro
      { path: 'administradores', component:AdminComponent},
      { path: 'configuracoes', component: ConfigComponent },
    ]
  },

  // Futuramente, aqui teremos as rotas do motorista
  // { path: 'motorista', component: MotoristaLayoutComponent, canActivate: [motoristaGuard], children: [...] }
];