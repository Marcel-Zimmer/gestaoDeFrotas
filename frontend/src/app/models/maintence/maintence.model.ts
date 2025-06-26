import { Veiculo } from "../veiculo/veiculo.model";

export interface Maintence {
  idVehicle: number;
  vehicle:Veiculo;
  type:string;
  price : string;
  currentMileage : string;
  description : string;
}