package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.model.Services;
import ir.ghazi.service_managment.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceService {
    private final ServiceRepository serviceRepository;

    public Services saveService(Services services) {
        return serviceRepository.save(services);
    }

    public List<Services> listServices() {
        List<Services> servicesList = serviceRepository.findAll();
        return servicesList;
    }
}
