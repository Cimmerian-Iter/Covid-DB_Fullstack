package org.polytech.covid.web;
import java.util.List;

import org.polytech.covid.domain.VaccinationCenter;
import org.polytech.covid.service.CenterNotFoundException;
import org.polytech.covid.service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class VaccinationCenterController {
    
    @Autowired
    private VaccinationCenterService CenterService;

    @GetMapping(path = "api/centers/city/{city}")
    public ResponseEntity<List<VaccinationCenter>> findAllByCity(@PathVariable String city){

        return ResponseEntity.ok(CenterService.findAllByCity(city));
    }

    @GetMapping(path = "api/centers/name/{name}")
    public ResponseEntity<List<VaccinationCenter>> findAllByName(@PathVariable String name){

        return ResponseEntity.ok(CenterService.findAllByName(name));
    }

    @GetMapping(path = "api/centers/id/{id}")
    public ResponseEntity<List<VaccinationCenter>> findAllById(@PathVariable int id){

        return ResponseEntity.ok(CenterService.findAllById(id));
    }

    @PostMapping(path = "api/centers/add")
    public ResponseEntity<VaccinationCenter> addCenter(@RequestBody VaccinationCenter center){
        int idFromJson = center.getId();
        VaccinationCenter savedCenter = CenterService.addCenter(center);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCenter);
    }

    public VaccinationCenterService getCenterService() {
        return CenterService;
    }

    public void setCenterService(VaccinationCenterService centerService) {
        CenterService = centerService;
    }
        @DeleteMapping("api/center/deletion/{id}")
    public ResponseEntity<String> deleteCenter(@PathVariable Integer id) {
        System.out.println("Inside deleteCenterById Controller");
        try {
            CenterService.deleteCenterById(id);
        return ResponseEntity.ok("Vaccination Center with ID " + id + " deleted successfully.");
    }   catch (CenterNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaccination Center with ID " + id + " not found.");
    }
}
}
