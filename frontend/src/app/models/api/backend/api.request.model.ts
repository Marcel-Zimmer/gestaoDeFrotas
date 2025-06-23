
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
  justify : string;
  status : string;
  cep: string;
  date : string;
  logradouro:string;
  complemento:string;
  unidade:string;
  bairro:string;
  localidade:string;
  uf:string;
  estado:string;
  regiao:string;
  ibge:string;
  gia:string;
  ddd:string;
  siafi:string;
  numero:string;
  
}