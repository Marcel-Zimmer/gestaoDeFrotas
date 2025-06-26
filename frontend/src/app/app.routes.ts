import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { VeiculosComponent } from './admin/pages/vehicles/vehicles.component';
import {DriversComponent} from './admin/pages/drivers/drivers.component';
import { adminGuard } from './auth/admin.guard'; // Importe o guarda
import { LayoutComponent as AdminLayoutComponent } from './admin/layout/layout.component';
import { LayoutComponent as DriverLayoutComponent } from './driver/layout/layout.component';
import { DashboardComponent as AdminDashboardComponent } from './admin/pages/dashboard/dashboard.component';
import { AdminComponent } from './admin/pages/admin/admin.component';
import { ConfigComponent } from './admin/pages/config/config.component';
import { RefuelingComponent } from './admin/pages/refueling/refueling.component';
import { DashboardComponent  as DriverDashboardComponent} from './driver/pages/dashboard/dashboard.component';
import { HistoryComponent } from './driver/pages/history/history.component';


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
      { path: 'refueling', component: RefuelingComponent },
    ]
  },
  //ROTAS MOTORISTA
  {
    path: 'driver',
    component: DriverLayoutComponent,
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' }, 
      { path: 'dashboard', component: DriverDashboardComponent },
      { path: 'history', component: HistoryComponent } 
    ]
  },

];