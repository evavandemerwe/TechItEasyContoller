package EvdM.TechItEasy.Services;

import EvdM.TechItEasy.Controllers.TelevisionController;
import EvdM.TechItEasy.Dtos.TelevisionDto;
import EvdM.TechItEasy.Exceptions.RecordNotFoundException;
import EvdM.TechItEasy.Models.Television;
import EvdM.TechItEasy.Repositories.TelevisionRepository;
import org.hibernate.criterion.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TelevisionService {
    private final TelevisionRepository televisionRepository;

    //Constructor injection instead of autowired
    public TelevisionService(TelevisionRepository repos){
        this.televisionRepository = repos;
    }

    public Object getTelevision(long id) {
        Optional<Television> televisionFound = televisionRepository.findById(id);

        if (!televisionFound.isPresent()) {
            throw new RecordNotFoundException("Television " + id +  " is not found");
        } else {
            Television foundTelevision = televisionRepository.getReferenceById(id);
            return foundTelevision;
        }

    }

    public Iterable<TelevisionDto> getTelevisions(){
        Iterable<Television> foundTelevisions = televisionRepository.findAll();

        ArrayList list = new ArrayList<>();
        for (Television t : foundTelevisions){
            TelevisionDto newTelevisionDto = new TelevisionDto();

            newTelevisionDto.price = t.getPrice();
            newTelevisionDto.originalStock = t.getOriginalStock();
            newTelevisionDto.ambiLight = t.getAmbiLight();
            newTelevisionDto.availableSize = t.getAvailableSize();
            newTelevisionDto.bluetooth = t.getBluetooth();
            newTelevisionDto.hdr = t.getHdr();
            newTelevisionDto.brand = t.getBrand();
            newTelevisionDto.name = t.getName();
            newTelevisionDto.screenQuality = t.getScreenQuality();
            newTelevisionDto.refreshRate = t.getRefreshRate();
            newTelevisionDto.screenType = t.getScreenType();
            newTelevisionDto.smartTv = t.getSmartTv();
            newTelevisionDto.wifi = t.getWifi();
            newTelevisionDto.voiceControl = t.getVoiceControl();
            newTelevisionDto.sold = t.getSold();

            list.add(newTelevisionDto);
        }
        return list;
    }
    public long createTelevision(TelevisionDto televisionDto){
        Television t = new Television();

        //map dto to entity
        t.setPrice(televisionDto.price);
        t.setOriginalStock(televisionDto.originalStock);
        t.setAmbiLight(televisionDto.ambiLight);
        t.setAvailableSize(televisionDto.availableSize);
        t.setBluetooth(televisionDto.bluetooth);
        t.setHdr(televisionDto.hdr);
        t.setBrand(televisionDto.brand);
        t.setName(televisionDto.name);
        t.setScreenQuality(televisionDto.screenQuality);
        t.setRefreshRate(televisionDto.refreshRate);
        t.setScreenType(televisionDto.screenType);
        t.setSmartTv(televisionDto.smartTv);
        t.setWifi(televisionDto.wifi);
        t.setVoiceControl(televisionDto.voiceControl);
        t.setSold(televisionDto.sold);

        Television savedTelevision =televisionRepository.save(t);

        return savedTelevision.getId();
    }

    public Object updateTelevision(long id, TelevisionDto televisionDto) {
        Optional<Television> televisionFound = televisionRepository.findById(id);

        if (!televisionFound.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            Television updatedTelevision = televisionRepository.getReferenceById(id);
            if (televisionDto.ambiLight != null) {
                updatedTelevision.setAmbiLight(televisionDto.ambiLight);
            }
            if (televisionDto.type != null) {
                updatedTelevision.setType(televisionDto.type);
            }
            if (televisionDto.brand != null) {
                updatedTelevision.setBrand(televisionDto.brand);
            }
            if (televisionDto.name != null) {
                updatedTelevision.setName(televisionDto.name);
            }
            if (televisionDto.price != null) {
                updatedTelevision.setPrice(televisionDto.price);
            }
            if (televisionDto.availableSize != null) {
                updatedTelevision.setAvailableSize(televisionDto.availableSize);
            }
            if (televisionDto.refreshRate != null) {
                updatedTelevision.setRefreshRate(televisionDto.refreshRate);
            }
            if (televisionDto.screenType != null) {
                updatedTelevision.setScreenType(televisionDto.screenType);
            }
            if (televisionDto.screenQuality != null) {
                updatedTelevision.setScreenQuality(televisionDto.screenQuality);
            }
            if (televisionDto.smartTv != null) {
                updatedTelevision.setSmartTv(televisionDto.smartTv);
            }
            if (televisionDto.wifi != null) {
                updatedTelevision.setWifi(televisionDto.wifi);
            }
            if (televisionDto.voiceControl != null) {
                updatedTelevision.setVoiceControl(televisionDto.voiceControl);
            }
            if (televisionDto.hdr != null) {
                updatedTelevision.setHdr(televisionDto.hdr);
            }
            if (televisionDto.bluetooth != null) {
                updatedTelevision.setBluetooth(televisionDto.bluetooth);
            }
            if (televisionDto.ambiLight != null) {
                updatedTelevision.setAmbiLight(televisionDto.ambiLight);
            }
            if (televisionDto.originalStock != null) {
                updatedTelevision.setOriginalStock(televisionDto.originalStock);
            }
            if (televisionDto.sold != null) {
                updatedTelevision.setSold(televisionDto.sold);
            }

            televisionRepository.save(updatedTelevision);

            return updatedTelevision;
        }
    }

    public Object deleteTelevision(long id){

        Optional<Television> foundTelevision = televisionRepository.findById(id);
        if(!foundTelevision.isPresent()){
            return ResponseEntity.notFound().build();
        }
        else{
            Television removedTelevision = televisionRepository.getReferenceById(id);
            televisionRepository.delete(removedTelevision);
            return ResponseEntity.ok("Television removed");
        }

    }
}
