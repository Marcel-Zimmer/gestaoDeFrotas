import { Veiculo } from './../veiculo/veiculo.model'; // Importe seu modelo de veículo

export interface ApiResponse {
  success: boolean;
  data: Veiculo[]; 
}