package htw.berlin.webtech.webtechprojekt;

import htw.berlin.webtech.webtechprojekt.api.Geld;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GeldRestController {

    private List<Geld> gelder;

    public GeldRestController() {
        gelder = new ArrayList<>();
        gelder.add(new Geld(1, "Mittagessen", 5, false));
        gelder.add(new Geld(2, "Schuhe verkauft", 50, true));
    }

    @GetMapping(path = "/api/v1/gelder")
    public ResponseEntity<List<Geld>> fetchGelder(){
        return ResponseEntity.ok(gelder);

    }

}
