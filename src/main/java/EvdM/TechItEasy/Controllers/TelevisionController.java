package EvdM.TechItEasy.Controllers;

import EvdM.TechItEasy.Models.Television;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@RestController
public class TelevisionController {

    private ArrayList<Television> televisions;

    public TelevisionController() {
        televisions = new ArrayList<>();
        Television t = new Television();
        t.setBrand("Philips");
        t.setType("oled");
        t.setName("Ambilight");
        t.setPrice(2000.00);
        t.setAvailableSize(43.00);
        t.setRefreshRate(100.00);
        t.setSmartTv(true);
        t.setWifi(true);
        t.setVoiceControl(false);
        t.setHdr(true);
        t.setBluetooth(true);
        t.setAmbiLight(true);
        t.setOriginalStock(1000);
        t.setSold(100);
        televisions.add(t);
    }

    // een GET-request voor alle televisies
    @GetMapping("/televisions")
    public ResponseEntity<Object> getAllTelevisions() {
        return new ResponseEntity<>(televisions, HttpStatus.OK);
    }

    // een GET-request voor 1 televisie
    @GetMapping(path = "/television/{id}")
    public ResponseEntity<Object> getTelevision(@PathVariable int id) {
        Television t = televisions.get(id);
        return ResponseEntity.ok("television");
    }

    // een POST-request voor 1 televisie
    @PostMapping("/television")
    public ResponseEntity<Object> createTelevision(@RequestBody Television t) {
        televisions.add(t);
        return ResponseEntity.created(null).body("television");
    }

    // een PUT-request voor 1 televisie
    @PutMapping("/television/{id}")
    public ResponseEntity<Object> editTelevision(@PathVariable int id, @RequestBody Television t) {
        if (id >= 0 && id < televisions.size()) {
            televisions.set(id, t);
            return ResponseEntity.ok("television");
        }
        return ResponseEntity.noContent().build();
    }

    // een DELETE-request voor 1 televisie
    @DeleteMapping("/television/delete/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable int id) {
        {
            if(id < televisions.size()){
                televisions.remove(id);
                return ResponseEntity.ok("television");
            }else {
                return ResponseEntity.noContent().build();
            }
        }
    }
}