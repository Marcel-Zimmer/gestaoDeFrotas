export interface LicencePlate {
  licence: string;
}

export interface ModelVehicle {
  model: string;
}

export interface TypeVehicle {
  type: string;
}

export interface YearVehicle {
  year: string;
}

export interface CurrentMileage {
  value: number;
}

export interface StatusVehicle {
  status: string;
}


export interface Veiculo {
  vehicleId: number;
  licencePlate: LicencePlate;
  modelVehicle: ModelVehicle;
  typeVehicle: TypeVehicle;
  yearVehicle: YearVehicle;
  currentMileage: CurrentMileage;
  statusVehicle: StatusVehicle;
}