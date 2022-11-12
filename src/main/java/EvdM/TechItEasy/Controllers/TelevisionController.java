package EvdM.TechItEasy.Controllers;

import EvdM.TechItEasy.Model.Television;
import com.sun.source.tree.ReturnTree;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class TelevisionController {

    private ArrayList<Television> televisions;

    public TelevisionController() {
        televisions = new ArrayList<>();
        Television t = new Television();
        t.setBrand("Philips");
        t.setModel("Ambilight");
        televisions.add(t);
    }

    // een GET-request voor alle televisies
    @GetMapping("/televisions")
    public ResponseEntity<Object> getAllTelevisions() {
        return new ResponseEntity<>(televisions, HttpStatus.OK);
    }

    // een GET-request voor 1 televisie
    @GetMapping("/television/{id}")
    public ResponseEntity<Television> getTelevision(@PathVariable int id) {
        Television t = televisions.get(id);
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    // een POST-request voor 1 televisie
    @PostMapping("/television")
    public ResponseEntity<Television> createTelevision(@RequestBody Television t) {
        televisions.add(t);
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    // een PUT-request voor 1 televisie
    @PutMapping("/television/{id}")
    public ResponseEntity<Object> editTelevision(@PathVariable int id, @RequestBody Television t) {
        if (id >= 0 && id < televisions.size()) {
            televisions.set(id, t);
            return new ResponseEntity<>(t, HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid id", HttpStatus.BAD_REQUEST);
    }

    // een DELETE-request voor 1 televisie
    @DeleteMapping("/televison/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable int t) {
        {
            for(int i = 0; i < televisions.size(); i++){
                televisions.remove(t);
                return new ResponseEntity<>("Removed!", HttpStatus.OK);
            }
            return new ResponseEntity<>("Name is not found in database", HttpStatus.BAD_REQUEST);
        }
    }
}