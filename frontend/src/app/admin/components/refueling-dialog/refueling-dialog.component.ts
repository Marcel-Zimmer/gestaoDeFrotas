import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { MatDialogRef, MatDialogModule } from '@angular/material/dialog';
import { forkJoin } from 'rxjs';

// Imports dos Módulos do Angular Material
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';

// Serviços e Modelos
import { DriversService } from '../../services/drivers/drivers.service'; 
import { VeiculoService} from '../../services/vehicles/veiculo.service';
import { RefuelingService } from '../../services/refueling/refueling.service';
import { Driver } from '../../../models/driver/driver.model';
import { Veiculo } from '../../../models/veiculo/veiculo.model';


@Component({
  selector: 'app-refueling-dialog',
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
    MatNativeDateModule
  ],
  templateUrl: './refueling-dialog.component.html',
  styleUrls: ['./refueling-dialog.component.scss']
})
export class RefuelingDialogComponent implements OnInit {
  
  form: FormGroup;
  
  // Arrays para popular os dropdowns
  public motoristas: Driver[] = []; 
  public vehicles: Veiculo[] = [];

  // Lista fixa para o tipo de combustível
  public fuelTypes = [
    { value: 'GASOLINA_COMUM', viewValue: 'Gasolina Comum' },
    { value: 'GASOLINA_ADITIVADA', viewValue: 'Gasolina Aditivada' },
    { value: 'ETANOL', viewValue: 'Etanol' },
    { value: 'DIESEL_COMUM', viewValue: 'Diesel Comum' },
    { value: 'DIESEL_S10', viewValue: 'Diesel S10' },
    { value: 'GNV', viewValue: 'GNV' },
  ];

  // Injeção de dependências
  private fb = inject(FormBuilder);
  private dialogRef = inject(MatDialogRef<RefuelingDialogComponent>);
  
  // Sugestão: Injetar serviços dedicados
  private refuelingService = inject(RefuelingService);
  private driverService = inject(DriversService);
  private vehicleService = inject(VeiculoService);

  constructor() {
    this.form = this.fb.group({
      idVehicle: [null, Validators.required],
      idDriver: [null, Validators.required], 
      date: [new Date(), Validators.required],
      typeRefueling: [null, Validators.required],
      price: [null, [Validators.required, Validators.min(0)]],
      currentMileage: [null, [Validators.required, Validators.min(0)]]
    });
  }

  ngOnInit(): void {
    this.loadDropdownData();
  }

  loadDropdownData(): void {
    const sources = {
      drivers: this.driverService.getDrivers(),
      vehicles: this.vehicleService.getVeiculos()
    };

    forkJoin(sources).subscribe({
      next: (responses) => {
        this.motoristas = responses.drivers.data;
        this.vehicles = responses.vehicles.data;
      },
      error: err => console.error('Erro ao carregar dados para os dropdowns', err)
    });
  }

  save(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched(); // Marca todos os campos para exibir erros
      return;
    }

    // Monta o payload para enviar ao backend
    const formValue = this.form.value;

    // Encontra o nome do motorista selecionado a partir do ID
    const selectedDriver = this.motoristas.find(d => d.driverId === formValue.idDriver);
    const driverName = selectedDriver ? selectedDriver.nameDriver : 'Não encontrado';

    // Cria o objeto final no formato que o backend espera
    const payload: any = {
      id: formValue.idVehicle,
      driverName: driverName,
      date: formValue.date,
      typeRefueling: formValue.typeRefueling,
      price: formValue.price,
      currentMileage: formValue.currentMileage
    };

    this.refuelingService.createRefueling(payload).subscribe({
      next: (response) => {
        console.log('Abastecimento registrado com sucesso!', response);
        this.dialogRef.close(true); // Fecha o dialog e retorna 'true' para indicar sucesso
      },
      error: (err) => {
        console.error('Erro ao registrar abastecimento', err);
        // Aqui você pode mostrar uma mensagem de erro para o usuário
      }
    });
  }

  close(): void {
    this.dialogRef.close();
  }
}