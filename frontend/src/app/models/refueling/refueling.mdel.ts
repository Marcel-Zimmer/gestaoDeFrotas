import { Veiculo } from "../veiculo/veiculo.model";

export interface Refueling {
  Id: number;
  vehicle:Veiculo;
  typeRefueling:string;
  nameDriver : string;
  refuelingDate : string;
  priceRefueling : number;
  currentMileage : number;

}