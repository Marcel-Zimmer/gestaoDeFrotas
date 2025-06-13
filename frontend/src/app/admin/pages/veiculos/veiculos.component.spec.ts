import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';

// Imports do Angular Material
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';

// Imports dos seus serviços e componentes
import { VeiculoService } from '../../services/veiculo.service';
import { VeiculoDialogComponent } from '../../components/veiculo-dialog/veiculo-dialog.component';

@Component({
  selector: 'app-veiculos',
  standalone: true,
  // Adicione aqui TODOS os módulos que o template HTML utiliza
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule
  ],
  templateUrl: './veiculos.component.html',
  styleUrls: ['./veiculos.component.scss']
})
export class VeiculosComponent implements OnInit {
  // Array que guardará os dados para a tabela
  public veiculos: any[] = [];
  
  // Define as colunas que a tabela irá exibir e a ordem delas
  public displayedColumns: string[] = ['placa', 'modelo', 'tipo', 'ano', 'status', 'acoes'];

  // Injeção de dependências da forma moderna
  private veiculoService = inject(VeiculoService);
  public dialog = inject(MatDialog);

  // O ngOnInit é um "gancho de ciclo de vida" que roda uma vez quando o componente é iniciado
  ngOnInit(): void {
    this.carregarVeiculos();
  }

  /**
   * Método responsável por chamar o serviço e carregar a lista de veículos.
   */
  carregarVeiculos(): void {
    this.veiculoService.getVeiculos().subscribe({
      next: (dados) => {
        this.veiculos = dados;
        console.log('Veículos carregados:', this.veiculos);
      },
      error: (erro) => {
        console.error('Erro ao carregar veículos:', erro);
        // Aqui você pode adicionar uma notificação para o usuário (ex: usando MatSnackBar)
      }
    });
  }

  /**
   * Método para abrir o dialog de criação/edição de veículo.
   * @param veiculo (Opcional) O objeto do veículo a ser editado. Se não for passado, abre em modo de criação.
   */
  abrirDialog(veiculo?: any): void {
    const dialogRef = this.dialog.open(VeiculoDialogComponent, {
      width: '450px',
      data: veiculo // Envia o dado do veículo para o dialog. Será 'undefined' no modo de criação.
    });

    // Escuta o evento de fechamento do dialog
    dialogRef.afterClosed().subscribe(result => {
      // 'result' conterá os dados do formulário se o usuário clicou em "Salvar"
      if (result) {
        // Chama o serviço para salvar (o serviço saberá se é pra criar ou editar)
        this.veiculoService.salvar(result).subscribe(() => {
          console.log('Veículo salvo com sucesso!');
          this.carregarVeiculos(); // Recarrega a lista para mostrar o item novo/atualizado
        });
      }
    });
  }

  /**
   * Método para excluir (inativar) um veículo.
   * @param id O ID do veículo a ser excluído.
   */
  excluirVeiculo(id: number): void {
    // Usamos o 'confirm' do navegador para uma confirmação simples
    if (confirm('Tem certeza que deseja inativar este veículo? A ação não pode ser desfeita.')) {
      this.veiculoService.excluir(id).subscribe({
        next: () => {
          console.log('Veículo inativado com sucesso!');
          this.carregarVeiculos(); // Recarrega a lista para remover o item
        },
        error: (erro) => {
          console.error('Erro ao inativar veículo:', erro);
        }
      });
    }
  }
}