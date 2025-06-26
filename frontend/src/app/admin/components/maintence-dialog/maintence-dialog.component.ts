import { Component, OnInit, Inject, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms'; // <<< IMPORTADO
import { MatDialogRef, MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { forkJoin } from 'rxjs';
import { MatNativeDateModule, provideNativeDateAdapter } from '@angular/material/core';

// Material Imports - LISTA CORRIGIDA E COMPLETA
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input'; // <<< IMPORTADO
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select'; // <<< IMPORTADO
import { MatDatepickerModule } from '@angular/material/datepicker'; // <<< IMPORTADO
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';


// Serviços e Modelos
import { VeiculoService } from '../../services/vehicles/veiculo.service'
import { Veiculo } from '../../../models/veiculo/veiculo.model';

@Component({
  selector: 'app-maintence-dialog', 
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,      
    MatButtonModule,
    MatSelectModule,    
    MatDatepickerModule, 
    MatNativeDateModule, 
    MatProgressSpinnerModule
  ],
  providers: [
    provideNativeDateAdapter() // <<< ADICIONE ESTA LINHA E O ARRAY 'providers'
  ],  
  templateUrl: './maintence-dialog.component.html',
  styleUrls: ['./maintence-dialog.component.scss']
})
export class MaintenceDialogComponent implements OnInit {
  
  form: FormGroup;
  isEditMode: boolean;
  vehicles: Veiculo[] = [];
  maintenanceTypes = [
    { value: 'PREVENTIVA', viewValue: 'Preventiva' },
    { value: 'CORRETIVA', viewValue: 'Corretiva' }
  ];
  
  isLoading = true; // <<< ADICIONADO: Para controlar o spinner de carregamento

  private fb = inject(FormBuilder);
  public dialogRef = inject(MatDialogRef<MaintenceDialogComponent>);
  private vehicleService = inject(VeiculoService);

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
    this.isEditMode = !!data;

    this.form = this.fb.group({
      id: [data?.id ?? null],
      idVehicle: [data?.idVehicle ?? null, Validators.required],
      date: [data?.date ?? new Date(), Validators.required],
      type: [data?.type ?? null, Validators.required],
      price: [data?.price ?? null, [Validators.required, Validators.min(0.01)]],
      currentMileage: [data?.currentMileage ?? null, [Validators.required, Validators.min(0)]],
      description: [data?.description ?? '', [Validators.required, Validators.minLength(5)]]
    });
  }

  ngOnInit(): void {
    // A lógica de carregar os veículos foi movida para um método dedicado
    this.loadVehicles();
  }

  loadVehicles(): void {
    this.isLoading = true; // Mostra o spinner
    this.vehicleService.getVeiculos().subscribe({ // Supondo que o método se chama getVehicles
      next: (response) => {
        this.vehicles = response.data;
        this.isLoading = false; // Esconde o spinner
      },
      error: (err) => {
        console.error("Erro ao carregar veículos", err);
        this.isLoading = false; // Esconde o spinner mesmo se der erro
        // Mostrar uma mensagem de erro para o usuário aqui
      }
    });
  }

  save(): void {
    if (this.form.invalid) {
      return;
    }
    this.dialogRef.close(this.form.value);
  }

  close(): void {
    this.dialogRef.close();
  }
}