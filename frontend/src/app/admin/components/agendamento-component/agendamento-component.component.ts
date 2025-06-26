import { Component, Inject, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core'

// Imports dos módulos do Angular Material para o formulário
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { AgendamentoService } from '../../services/agendamento/agendamento.service';
import { Driver } from '../../../models/driver/driver.model';
import { Veiculo } from '../../../models/veiculo/veiculo.model';
import { forkJoin } from 'rxjs';




@Component({
  selector: 'app-agendamento-component',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule, // Essencial para FormGroups
    MatDialogModule,     // Para as diretivas mat-dialog-title, content, etc.
    MatFormFieldModule,  // Para <mat-form-field>
    MatInputModule,      // Para a diretiva matInput nos campos
    MatButtonModule,      // Para os botões mat-button
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule
  ],
  templateUrl: './agendamento-component.component.html',
  styleUrl: './agendamento-component.component.scss'
})
export class AgendamentoComponentComponent {
  
  form: FormGroup;
  isEditMode: boolean;
  private agendamentoService = inject(AgendamentoService);
  private fb = inject(FormBuilder);
  public dialogRef = inject(MatDialogRef<AgendamentoComponentComponent>);
  public motoristas: Driver[] = []; 
  public vehicles : Veiculo[] = [];


  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
    this.isEditMode = !!data;
    this.form = this.fb.group({
      id: [null],
      idDriver: [null, Validators.required],
      idVehicle: [null, Validators.required],
      justify: ['', Validators.required],
      status: ['AGENDADO', Validators.required], 
      date: ['', Validators.required],
      cep: ['', [Validators.required, Validators.pattern(/^\d{8}$/)]], 
      logradouro: [null, Validators.required],
      bairro: [null, Validators.required],
      localidade: [null, Validators.required],
      uf: [null, Validators.required],
      numero: ['', Validators.required],
      complemento: ['']
    });

  }

  ngOnInit(): void {
    const sources = {
      drivers: this.agendamentoService.getDrivers(),
      vehicles: this.agendamentoService.getDisponibleVeicles()
    };

    // 3. Use forkJoin para esperar por todas as respostas
    forkJoin(sources).subscribe({
      next: (responses) => {
        // 'responses' é um objeto { drivers: ApiResponseDriver, vehicles: ApiResponseVehicle }

        // 4. Primeiro, popule os arrays para os dropdowns
        this.motoristas = responses.drivers.data;
        this.vehicles = responses.vehicles.data;

        // 5. AGORA, e somente agora, preencha o formulário se for modo de edição
        if (this.isEditMode) {
          this.preencherFormularioParaEdicao();
        }
      },
      error: err => {
        console.error('Erro ao carregar dados iniciais para o dialog', err);
        // Mostrar uma mensagem de erro para o usuário aqui
      }
    });
  }

  preencherFormularioParaEdicao(): void {
    this.form.patchValue({
      id: this.data.tripId,
      idDriver: this.data.idDriver,
      idVehicle: this.data.idVehicle,
      justify: this.data.justify,
      status: this.data.statusTrip,
      date: this.data.date,
      cep:this.data.address.zipCode,
      logradouro:this.data.address.street,
      numero: this.data.address.numberAddress,
      bairro:this.data.address.neighborhood,
      localidade:this.data.address.city,
      uf:this.data.address.stateAbbreviation,
      complemento:this.data.address.complement,
    });
  
  }

  status = [
    { value: 'AGENDADO', viewValue: 'Agendado' },
    { value: 'EM_VIAGEM', viewValue: 'Em viagem' },
    { value: 'FINALIZADO', viewValue: 'Finalizado' },
    { value: 'CANCELADO', viewValue: 'Cancelado' }
  ];


  buscarCep(): void {
    const cep = this.form.get('cep')?.value;
    if (cep && cep.length === 8) {
      this.agendamentoService.getAddress(cep).subscribe(dadosDoEndereco => {
        this.form.patchValue({
          logradouro: dadosDoEndereco.logradouro,
          unidade:dadosDoEndereco.unidade,
          bairro: dadosDoEndereco.bairro,
          localidade: dadosDoEndereco.localidade, 
          uf: dadosDoEndereco.uf,
          estado:dadosDoEndereco.estado,
          regiao : dadosDoEndereco.regiao,
          ibge : dadosDoEndereco.ibge,
          gia:dadosDoEndereco.gia,
          ddd:dadosDoEndereco.ddd,
          siafi : dadosDoEndereco.siafi,
        });

      });
    }
  }

  save(): void {
    
    if (this.form.valid) {
      this.dialogRef.close(this.form.value);
    }
    
    
  }

  close(): void {
    this.dialogRef.close();
  }
}
