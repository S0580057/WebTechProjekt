package htw.berlin.webtech.webtechprojekt.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Repository
public interface GeldRepository extends JpaRepository<GeldEntity, Long> {

    List<GeldEntity> findAllByName(String name);

}
