
export interface VehicleRequest {
  id: number;
  licencePlate: string;
  modelVehicle: string;
  typeVehicle: string;
  yearVehicle: number;
  currentMileage: number;
  statusVehicle: string;
}

export interface AgendamentoRequest{
  id :number;
  idVehicle : number;
  idDriver : number;
  date : string;
  justify : string;
  status : string
}