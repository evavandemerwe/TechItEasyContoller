package EvdM.TechItEasy.Controllers;

import EvdM.TechItEasy.Dtos.TelevisionDto;
import EvdM.TechItEasy.Services.TelevisionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;


@RestController
public class TelevisionController {

    private final TelevisionService televisionService;

    //Constructor injection instead of autowired
    public TelevisionController(TelevisionService service) {
        this.televisionService = service;
    }

    // een GET-request voor alle televisies
    @GetMapping("/televisions")
    public ResponseEntity<Iterable<TelevisionDto>> getAllTelevisions() {
        return ResponseEntity.ok(televisionService.getTelevisions());
    }

    @GetMapping("/television/{id}")
    public ResponseEntity<Object> getTelevisionById(@PathVariable long id) {
        return ResponseEntity.ok(televisionService.getTelevision(id));
    }

    // een POST-request voor 1 televisie
    @PostMapping("/television")
    public ResponseEntity<String> createTelevision(@Valid @RequestBody TelevisionDto televisionDto, BindingResult br) {
    if(br.hasErrors()){
        //something went wrong
        StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
         return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
    } else {
        Long createdId = televisionService.createTelevision(televisionDto);
        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/televisions/" + createdId).toUriString());
        return ResponseEntity.created(uri).body("Television created!");

    }
}

    // een PUT-request voor 1 televisie
    @PutMapping("/television/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable  long id, @Valid @RequestBody TelevisionDto televisionDto, BindingResult br){
        if(br.hasErrors()) {
            //something went wrong
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }else{
            Object updatedTelevision = televisionService.updateTelevision(id, televisionDto);

            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/televisions/").toUriString());
            return ResponseEntity.created(uri).body("Television updated!");
        }
    }


    // een DELETE-request voor 1 televisie
    @DeleteMapping("/television/delete/{id}")
    public Object removeTelevision(@PathVariable long id){
        return televisionService.deleteTelevision(id);
    }
}