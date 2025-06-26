import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const adminGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);

  const usuarioString = localStorage.getItem('usuario_logado');

  if (usuarioString) {
    const usuario = JSON.parse(usuarioString);
    if (usuario && usuario.perfil === 'ADMINISTRADOR') {
      return true; 
    }
  }

  router.navigate(['/login']);
  return false; 
};