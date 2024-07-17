package ir.ghazi.service_managment.controller;

import ir.ghazi.service_managment.dto.client.FilterClientResponse;
import ir.ghazi.service_managment.dto.offer.OfferFilterResponse;
import ir.ghazi.service_managment.dto.offer.OfferRequest;
import ir.ghazi.service_managment.dto.offer.OfferResponse;
import ir.ghazi.service_managment.dto.specialist.FilterSpecialistResponse;
import ir.ghazi.service_managment.dto.specialist.SpecialistRequest;
import ir.ghazi.service_managment.dto.specialist.SpecialistResponse;
import ir.ghazi.service_managment.enums.OfferSituation;
import ir.ghazi.service_managment.mapper.OfferMapper;
import ir.ghazi.service_managment.mapper.SpecialistMapper;
import ir.ghazi.service_managment.model.Offer;
import ir.ghazi.service_managment.model.Order;
import ir.ghazi.service_managment.model.Specialist;
import ir.ghazi.service_managment.service.OfferService;
import ir.ghazi.service_managment.service.SpecialistService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/specialist")
public class SpecialistController {

    private final SpecialistService specialistService;

    private final OfferService offerService;

    @PostMapping("/register-specialist")
    @PermitAll
    public ResponseEntity<SpecialistResponse> registerSpecialist(@RequestBody SpecialistRequest request, String filePath) {
        Specialist mappedSpecialist = SpecialistMapper.INSTANCE.specialistSaveRequestToModel(request);
        Specialist savedSpecialist = specialistService.saveSpecialist(mappedSpecialist, filePath);
        return new ResponseEntity<>(SpecialistMapper.INSTANCE.modelToSpecialistSaveResponse(savedSpecialist),
                HttpStatus.CREATED);
    }

    @GetMapping("/sign-in")
    @PreAuthorize("hasRole('ROLE_SPECIALIST')")
    public void signInSpecialist(@RequestParam String email, String password) {
        specialistService.specialistSignIn(email, password);
    }

    @PatchMapping("/change-password-specialist")
    @PreAuthorize("hasRole('ROLE_SPECIALIST')")
    public void changePassword(@RequestParam String email, String passwordRequest) {
        Specialist specialist = specialistService.findByEmail(email);
        specialist.setPassword(passwordRequest);
        specialistService.updateSpecialist(specialist);
    }

    @PostMapping("/add-offer")
    @PreAuthorize("hasRole('ROLE_SPECIALIST')")
    public ResponseEntity<OfferResponse> addOffer
            (@RequestBody OfferRequest request, Long order, Long specialist) {
        Offer mappedOffer = OfferMapper.INSTANCE.offerSaveRequestToModel(request);
        Offer savedOffer = offerService.saveOffer(mappedOffer, order, specialist);
        return new ResponseEntity<>(OfferMapper.INSTANCE.modelToOfferSaveResponse(savedOffer), HttpStatus.CREATED);
    }

    @GetMapping("/filter-offer")
    @PreAuthorize("hasRole('ROLE_SPECIALIST')")
    public List<OfferFilterResponse> filterOffer(@RequestParam String column, Long value,
                                                 String column1, String value1) {
        List<Offer> offers =
                offerService.filterSpecialistByOfferSituation(column, value, column1, value1);
        List<OfferFilterResponse> offerResponseList = new ArrayList<>();

        for (Offer offer : offers) {
            OfferFilterResponse offerFilterResponse =
                    OfferMapper.INSTANCE.modelToOfferFilterResponse(offer);
            offerResponseList.add(offerFilterResponse);
        }
        return offerResponseList;
    }
}
