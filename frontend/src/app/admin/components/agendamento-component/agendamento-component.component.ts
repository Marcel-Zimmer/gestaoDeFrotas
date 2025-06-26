import { Component, Inject, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { NgxMaskDirective, provideNgxMask } from 'ngx-mask';
import { forkJoin } from 'rxjs';

// Imports dos módulos do Angular Material para o formulário
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { AgendamentoService } from '../../services/agendamento/agendamento.service';
import { Driver } from '../../../models/driver/driver.model';
import { Veiculo } from '../../../models/veiculo/veiculo.model';

@Component({
  selector: 'app-agendamento-component',
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
    NgxMaskDirective,
  ],
  providers: [provideNgxMask()],
  templateUrl: './agendamento-component.component.html',
  styleUrls: ['./agendamento-component.component.scss']
})
export class AgendamentoComponentComponent implements OnInit {
  
  form: FormGroup;
  isEditMode: boolean;
  private agendamentoService = inject(AgendamentoService);
  private fb = inject(FormBuilder);
  public dialogRef = inject(MatDialogRef<AgendamentoComponentComponent>);
  public motoristas: Driver[] = [];
  public vehicles: Veiculo[] = [];

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
    this.isEditMode = !!data;
    this.form = this.fb.group({
      id: [null],
      idDriver: [null, Validators.required],
      idVehicle: [null, Validators.required],
      justify: ['', Validators.required],
      status: ['AGENDADO', Validators.required],
      // --- MUDANÇA AQUI: Separamos o campo de data e hora ---
      appointmentDate: [null, Validators.required], // Para o calendário (objeto Date)
      appointmentTime: [null, Validators.required], // Para o horário (string "HH:mm")
      // --- Fim da mudança ---
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

    forkJoin(sources).subscribe({
      next: (responses) => {
        this.motoristas = responses.drivers.data;
        this.vehicles = responses.vehicles.data;
        if (this.isEditMode) {
          this.preencherFormularioParaEdicao();
        }
      },
      error: err => {
        console.error('Erro ao carregar dados iniciais para o dialog', err);
      }
    });
  }

  preencherFormularioParaEdicao(): void {
    // --- MUDANÇA AQUI: Separamos a data e a hora para preencher os campos ---
    const dateObj = new Date(this.data.date); // Cria um objeto Date a partir do valor recebido
    const hours = dateObj.getHours().toString().padStart(2, '0');
    const minutes = dateObj.getMinutes().toString().padStart(2, '0');
    const timeString = `${hours}:${minutes}`; // Formata a hora como "HH:mm"

    this.form.patchValue({
      id: this.data.tripId,
      idDriver: this.data.idDriver,
      idVehicle: this.data.idVehicle,
      justify: this.data.justify,
      status: this.data.statusTrip,
      // Preenche os novos campos separados
      appointmentDate: dateObj,
      appointmentTime: timeString,
      cep: this.data.address.zipCode,
      logradouro: this.data.address.street,
      numero: this.data.address.numberAddress,
      bairro: this.data.address.neighborhood,
      localidade: this.data.address.city,
      uf: this.data.address.stateAbbreviation,
      complemento: this.data.address.complement,
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
          bairro: dadosDoEndereco.bairro,
          localidade: dadosDoEndereco.localidade,
          uf: dadosDoEndereco.uf,
        });
      });
    }
  }

  // --- NOVA FUNÇÃO: Para combinar data e hora ---
  private combineDateAndTime(): Date | null {
    const dateValue = this.form.value.appointmentDate;
    const timeValue = this.form.value.appointmentTime; 

    if (!dateValue || !timeValue) {
      return null;
    }
    const [hours, minutes] = timeValue.split(':').map(Number);
    const combinedDate = new Date(dateValue.getTime());
    combinedDate.setHours(hours);
    combinedDate.setMinutes(minutes);
    combinedDate.setSeconds(0);
    return combinedDate;
  }

  save(): void {
    if (this.form.invalid) {
      console.error('Formulário inválido. Verifique todos os campos.');
      return;
    }

    // --- MUDANÇA AQUI: Preparamos o objeto final para o backend ---
    const combinedDateTime = this.combineDateAndTime();
    
    // Cria um objeto de payload final, substituindo os campos separados pelo campo 'date' combinado
    const finalPayload = {
      ...this.form.value,
      date: combinedDateTime ? combinedDateTime.toISOString() : null
    };

    // Remove os campos separados, pois não são necessários no payload final
    delete finalPayload.appointmentDate;
    delete finalPayload.appointmentTime;

    this.dialogRef.close(finalPayload);
  }

  close(): void {
    this.dialogRef.close();
  }
}
