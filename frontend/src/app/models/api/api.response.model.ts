import { Trip } from '../trip/trip.model';
import { Veiculo } from './../veiculo/veiculo.model'; // Importe seu modelo de veículo

export interface ApiResponseVehicle {
  success: boolean;
  message : string;
  data: Veiculo[]; 
}

export interface ApiResponseTrip{
  success: boolean;
  message : string;
  data: Trip[]; 
}