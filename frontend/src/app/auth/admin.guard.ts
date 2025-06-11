import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const adminGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);

  // Exemplo de como você poderia pegar o perfil do usuário
  // Você precisará adaptar isso à forma como você salva os dados após o login
  const usuarioString = localStorage.getItem('usuario_logado');

  if (usuarioString) {
    const usuario = JSON.parse(usuarioString);
    // Verifique se o usuário tem o perfil de administrador
    if (usuario && usuario.perfil === 'ADMINISTRADOR') {
      return true; // Permissão concedida
    }
  }

  // Se não tiver permissão, redireciona para a página de login
  router.navigate(['/login']);
  return false; // Permissão negada
};