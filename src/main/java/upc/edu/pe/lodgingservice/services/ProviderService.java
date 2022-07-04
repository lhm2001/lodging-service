package upc.edu.pe.lodgingservice.services;

import upc.edu.pe.lodgingservice.entities.Provider;

public interface ProviderService extends  CrudService<Provider,Long>{

    Provider createProvider(Provider provider);
}
