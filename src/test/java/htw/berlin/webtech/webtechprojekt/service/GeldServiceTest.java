package htw.berlin.webtech.webtechprojekt.service;

import htw.berlin.webtech.webtechprojekt.persistence.GeldRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GeldServiceTest implements WithAssertions {

    @Mock
    private GeldRepository repository;

    @InjectMocks
    private GeldService toTest;

    @Test
    @DisplayName("Es soll der wert true zurückgegeben werden, wenn etwas erfolgreich gelöscht wurde")
    void shouldReturnTrueIfDeleteSuccessful(){

        Long givenId = 200L;
        doReturn(true).when(repository).existsById(givenId);


        boolean result = toTest.deleteById(givenId);


        verify(repository).deleteById(givenId);
        assertThat(result).isTrue();

    }

    @Test
    @DisplayName("Es soll der Wert false zurück gegeben werden, wenn die Einnahme bzw Ausgabe nicht existiert")
    void shouldReturnFalseIfGeldNotExist(){

        Long givenId = 200L;
        doReturn(false).when(repository).existsById(givenId);

        boolean result = toTest.deleteById(givenId);

        verifyNoMoreInteractions(repository);
        assertThat(result).isFalse();
    }

}
