package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.model.SubService;
import ir.ghazi.service_managment.repository.SubServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SubServiceService {
    private final SubServiceRepository subServiceRepository;

    public SubServiceService(SubServiceRepository subServiceRepository) {
        this.subServiceRepository = subServiceRepository;
    }

    public SubService savaSubService(SubService subService) {
        try {
            if (subServiceRepository.findByName(subService.getName()).isPresent()) {
                log.warn(subService.getName() + " has already exist , Enter another name !");
            } else {
                subServiceRepository.save(subService);
                log.info(subService.getName() + " has successfully add !");
            }
            return subService;
        } catch (Exception e) {
            log.error("An error in Save SubService");
            return null;
        }
    }

    public List<SubService> subServiceList() {
        List<SubService> subServiceList = subServiceRepository.findAll();
        return subServiceList;
    }
}
