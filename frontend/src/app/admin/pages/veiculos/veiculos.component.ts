import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';

// Imports do Angular Material para esta página
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';

// Imports dos seus serviços e componentes customizados
import { VeiculoService } from '../../services/veiculo.service';
import { VeiculoDialogComponent } from '../../components/veiculo-dialog/veiculo-dialog.component';

@Component({
  selector: 'app-veiculos',
  standalone: true,
  // Todos os módulos que o template HTML desta página precisa
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
  public dataSource = new MatTableDataSource<any>(); 
  
  // Define as colunas que a tabela irá exibir e a ordem delas.
  // Deve corresponder exatamente aos 'matColumnDef' no seu HTML.
  public displayedColumns: string[] = ['placa', 'modelo', 'tipo', 'ano', 'quilometragem', 'status', 'acoes'];

  // Injeção de dependências da forma moderna
  private veiculoService = inject(VeiculoService);
  public dialog = inject(MatDialog);

  // ngOnInit é chamado uma vez quando o componente é inicializado.
  ngOnInit(): void {
    this.carregarVeiculos();
  }

  /**
   * Método responsável por chamar o serviço e carregar a lista de veículos.
   */
  carregarVeiculos(): void {
    this.veiculoService.getVeiculos().subscribe({
      next: (dados) => {
        // MUDANÇA 2: Atribua os dados à propriedade .data do dataSource
        this.dataSource.data = dados; // (Lembre-se de usar .data se sua API for envelopada)
      },
      // ...
    });
  }
  /*
  /**
   * Método para abrir o dialog de criação/edição de veículo.
   * @param veiculo (Opcional) O objeto do veículo a ser editado.
   */
  abrirDialog(veiculo?: any): void {
    const dialogRef = this.dialog.open(VeiculoDialogComponent, {
      width: '450px',
      disableClose: true, // Impede que o dialog seja fechado clicando fora dele
      data: veiculo // Envia o dado do veículo para o dialog. Será 'undefined' no modo de criação.
    });

    // Escuta o evento de fechamento do dialog
    dialogRef.afterClosed().subscribe(result => {
      // 'result' conterá os dados do formulário se o usuário clicou em "Salvar"
      if (result) {
        this.veiculoService.salvar(result).subscribe({
          next: () => {
            this.carregarVeiculos(); // Recarrega a lista para mostrar o item novo/atualizado
          },
          error: (erro) => {
            console.error('Erro ao salvar veículo:', erro);
          }
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
    if (confirm('Tem certeza que deseja inativar este veículo?')) {
      this.veiculoService.excluir(id).subscribe({
        next: () => {
          this.carregarVeiculos(); // Recarrega a lista para remover o item
        },
        error: (erro) => {
          console.error('Erro ao inativar veículo:', erro);
        }
      });
    }
  }
  
}