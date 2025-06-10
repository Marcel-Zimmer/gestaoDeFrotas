import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router'; // 1. IMPORTAR AQUI

@Component({
  selector: 'app-root',
  standalone: true, // 2. GARANTIR QUE ESTÁ 'true'
  imports: [CommonModule, RouterOutlet], // 3. ADICIONAR NOS IMPORTS
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'gestao-de-frotas';
}