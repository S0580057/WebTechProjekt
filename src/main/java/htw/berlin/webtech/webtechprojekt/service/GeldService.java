package htw.berlin.webtech.webtechprojekt.service;

import htw.berlin.webtech.webtechprojekt.api.Geld;
import htw.berlin.webtech.webtechprojekt.api.GeldManipulationRequest;
import htw.berlin.webtech.webtechprojekt.persistence.GeldEntity;
import htw.berlin.webtech.webtechprojekt.persistence.GeldRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeldService {

    private final GeldRepository geldRepository;

    public GeldService(GeldRepository geldRepository) {
        this.geldRepository = geldRepository;
    }

    public List<Geld> findAll(){
        List<GeldEntity> gelder = geldRepository.findAll();
        return gelder.stream().map(this::transformEntity).
                collect(Collectors.toList());
    }

    public Geld findById(Long id){
        var geldEntity = geldRepository.findById(id);
        return geldEntity.map(this::transformEntity).orElse(null);
    }

    public Geld create(GeldManipulationRequest request){
        System.out.println("test");
        var geldEntity = new GeldEntity(request.getName(), request.getGeldBetrag(), request.isEinnahme());
        geldEntity = geldRepository.save(geldEntity);
        return transformEntity(geldEntity);
    }

    public Geld update(Long id, GeldManipulationRequest request){
        var geldEntityOptional = geldRepository.findById(id);
        if (geldEntityOptional.isEmpty()){
            return null;
        }

        var geldEntity = geldEntityOptional.get();
        geldEntity.setName(request.getName());
        geldEntity.setGeldBetrag(request.getGeldBetrag());
        geldEntity.setEinnahme(request.isEinnahme());
        geldEntity = geldRepository.save(geldEntity);

        return transformEntity(geldEntity);
    }

    public boolean deleteById(Long id) {
        if(!geldRepository.existsById(id)){
            return false;
        }

        geldRepository.deleteById(id);
        return true;
    }

    private Geld transformEntity(GeldEntity geldEntity){

        return new Geld(
                geldEntity.getId(),
                geldEntity.getName(),
                geldEntity.getGeldBetrag(),
                geldEntity.getEinnahme()
        );
    }
}
