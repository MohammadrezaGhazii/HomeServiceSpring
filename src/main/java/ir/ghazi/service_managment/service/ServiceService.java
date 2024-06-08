package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.model.Services;
import ir.ghazi.service_managment.repository.ServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ServiceService {
    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Services saveService(Services services){
        return serviceRepository.save(services);
    }
}
