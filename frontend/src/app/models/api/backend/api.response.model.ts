import { Driver } from '../../driver/driver.model';
import { Maintence } from '../../maintence/maintence.model';
import { Refueling } from '../../refueling/refueling.mdel';
import { Trip } from '../../trip/trip.model';
import { Veiculo } from '../../veiculo/veiculo.model'; // Importe seu modelo de veículo

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
export interface ApiResponseDriver{
  success: boolean;
  message : string;
  data: Driver[]; 
}

export interface ApiResponseRefueling{
  success: boolean;
  message : string;
  data: Refueling[];   
}

export interface ApiResponseMaintence{
  success: boolean;
  message : string;
  data: Maintence[];   
}