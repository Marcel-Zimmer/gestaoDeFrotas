import { Component, Inject } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common'; // Importe DatePipe
import { MatDialogRef, MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { Trip } from '../../../models/trip/trip.model'; // Seu modelo de Trip

// Material Imports
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';

@Component({
  selector: 'app-trip-details-dialog',
  standalone: true,
  imports: [
    CommonModule,
    MatDialogModule,
    MatButtonModule,
    MatCardModule,
    MatIconModule,
    MatDividerModule,
    DatePipe 
  ],
  templateUrl: './trip-details-dialog.component.html',
  styleUrls: ['./trip-details-dialog.component.scss']
})
export class TripDetailsDialogComponent {
  
  // O construtor injeta os dados da viagem diretamente na propriedade 'trip'
  constructor(
    public dialogRef: MatDialogRef<TripDetailsDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public trip: Trip
  ) {}

  close(): void {
    this.dialogRef.close();
  }
}