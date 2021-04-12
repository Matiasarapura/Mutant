package meli.challenge.mutante;

import meli.challenge.mutante.component.MutantAnalizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


 class MutantAnalizerTest {

    private MutantAnalizer mutantAnalizer;

    @BeforeEach
     void init(){
        mutantAnalizer = new MutantAnalizer();
    }
    @Test
     void validateDnaValid(){
        var validList = new ArrayList(Arrays.asList("AAAA", "CCCC", "TTTT", "GGGG"));
        assertTrue(mutantAnalizer.validateSequenceDna(validList));

    }
    @Test
     void validateDnaInValid(){
        var invalidList = new ArrayList(Arrays.asList("THIS", "DNA.", "-IS-", "BAD."));
        assertFalse(mutantAnalizer.validateSequenceDna(invalidList));
    }
    @Test
     void validateDnaEmpty(){
        var invalidList = new ArrayList(Arrays.asList());
        assertFalse(mutantAnalizer.validateSequenceDna(invalidList));
    }

    @Test
    void isMutantRowSequenceAAAA(){
        var validList = new ArrayList(Arrays.asList("AAAA", "CCCC", "TTTT", "GGGG"));
        assertTrue(mutantAnalizer.isMutant(validList));
    }
    @Test
    void isMutantRowSequenceCCCC(){
        var validList = new ArrayList(Arrays.asList("CCCC", "CCCC", "TTTT", "GGGG"));
        assertTrue(mutantAnalizer.isMutant(validList));
    }
    @Test
     void isMutantRowSequenceTTTT(){
        var validList = new ArrayList(Arrays.asList("TTTT", "CCCC", "TTTT", "GGGG"));
        assertTrue(mutantAnalizer.isMutant(validList));
    }
    @Test
     void isMutantRowSequenceGGGG(){
        var validList = new ArrayList(Arrays.asList("GGGG", "CCCC", "TTTT", "GGGG"));
        assertTrue(mutantAnalizer.isMutant(validList));
    }
    @Test
     void isMutantColumnSequence(){
        var validList = new ArrayList(Arrays.asList("ACCC", "ATTT", "AGGG", "AGTC"));
        assertTrue(mutantAnalizer.isMutant(validList));
    }
    @Test
     void isMutantDiagonalSequence(){
        var validList = new ArrayList(Arrays.asList("ACCC", "TATT", "GGAG", "AGTA"));
        assertTrue(mutantAnalizer.isMutant(validList));
    }
    @Test
     void isMutantNoSequence(){
        var validList = new ArrayList(Arrays.asList("ACCC", "ATAT", "GGAG", "AGTA"));
        assertFalse(mutantAnalizer.isMutant(validList));
    }

    @Test
     void isMutantEmpty(){
        var invalidList = new ArrayList(Arrays.asList());
        assertFalse(mutantAnalizer.isMutant(invalidList));
    }
}
