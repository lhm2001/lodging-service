package upc.edu.pe.lodgingservice.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.pe.lodgingservice.entities.Provider;
import upc.edu.pe.lodgingservice.repositories.ProviderRepository;
import upc.edu.pe.lodgingservice.services.ProviderService;

import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public List<Provider> findAll() throws Exception {
        return providerRepository.findAll();
    }


    @Override
    public Provider createProvider(Provider provider) {
        return providerRepository.save(provider);
    }
}
