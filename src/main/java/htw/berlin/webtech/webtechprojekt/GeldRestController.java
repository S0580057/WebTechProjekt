package htw.berlin.webtech.webtechprojekt;

import htw.berlin.webtech.webtechprojekt.api.Geld;
import htw.berlin.webtech.webtechprojekt.api.GeldCreateRequest;
import htw.berlin.webtech.webtechprojekt.persistence.GeldRepository;
import htw.berlin.webtech.webtechprojekt.service.GeldService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class GeldRestController {

    private final GeldService geldService;

    public GeldRestController(GeldService geldService) {
        this.geldService = geldService;
    }

    @GetMapping(path = "/api/v1/gelder")
    public ResponseEntity<List<Geld>> fetchGelder(){
        return ResponseEntity.ok(geldService.findAll());

    }

    @PostMapping(path = "/api/v1/gelder")
    public ResponseEntity<Void> createGeld(@RequestBody GeldCreateRequest request) throws URISyntaxException {
        var geld = geldService.create(request);
        URI uri = new URI("/api/v1/gelder/" + geld.getId());
        return ResponseEntity.created(uri).build();
    }

}
