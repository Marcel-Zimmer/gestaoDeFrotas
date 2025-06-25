import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { MatDialogRef, MatDialogModule } from '@angular/material/dialog';
import { VeiculoService } from '../../../admin/services/vehicles/veiculo.service'; // Usando um serviço dedicado
import { MaintenanceService } from '../../services/maintenance.service';
import { Veiculo } from '../../../models/veiculo/veiculo.model';

// Material Imports
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-report-issue-dialog',
  standalone: true,
  imports: [
    CommonModule, ReactiveFormsModule, MatDialogModule, MatFormFieldModule,
    MatInputModule, MatButtonModule, MatSelectModule
  ],
  templateUrl: './report-issue-dialog.component.html',
  styleUrls: ['./report-issue-dialog.component.scss']
})
export class ReportIssueDialogComponent implements OnInit {
  
  form: FormGroup;
  vehicles: Veiculo[] = [];

  private fb = inject(FormBuilder);
  private dialogRef = inject(MatDialogRef<ReportIssueDialogComponent>);
  private vehicleService = inject(VeiculoService);
  private ocorrenciaService = inject(MaintenanceService);

  constructor() {
    this.form = this.fb.group({
      idVehicle: [null, Validators.required],
      descricao: ['', [Validators.required, Validators.minLength(10)]]
    });
  }

  ngOnInit(): void {
    // Carrega a lista de veículos para o dropdown
    this.vehicleService.getVeiculos().subscribe(response => {
      this.vehicles = response.data;
    });
  }

  save(): void {
    if (this.form.invalid) {
      return;
    }
    this.ocorrenciaService.createOcorrencia(this.form.value).subscribe({
      next: () => this.dialogRef.close(true),
      error: (err) => console.error('Erro ao relatar ocorrência', err)
    });
  }

  close(): void {
    this.dialogRef.close();
  }
}