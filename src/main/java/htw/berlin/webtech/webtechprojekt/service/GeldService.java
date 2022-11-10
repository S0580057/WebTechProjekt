package htw.berlin.webtech.webtechprojekt.service;

import htw.berlin.webtech.webtechprojekt.api.Geld;
import htw.berlin.webtech.webtechprojekt.api.GeldCreateRequest;
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

    public Geld create(GeldCreateRequest request){
        var geldEntity = new GeldEntity(request.getName(), request.getGeldBetrag(), request.isEinnahme());
        geldEntity = geldRepository.save(geldEntity);
        return transformEntity(geldEntity);
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
