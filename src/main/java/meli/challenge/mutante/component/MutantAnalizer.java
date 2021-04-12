package meli.challenge.mutante.component;


import lombok.extern.log4j.Log4j2;
import meli.challenge.mutante.constants.Constants;
import meli.challenge.mutante.helper.MatrixHelper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Log4j2
public class MutantAnalizer {



    public  boolean isMutant(List<String> dna) {

        if(analizeSequence(dna)){
            log.info("Mutant Sequence Found in row");

            return true;
        }
        char [][] dnaMatrix = MatrixHelper.stringListToMatrix(dna);
        if(analizeSequence(MatrixHelper.transponeMatrix(dnaMatrix))){
            log.info("Mutant Sequence Found in column");

            return true;
        }else if(analizeSequence(MatrixHelper.getDiagonals(dnaMatrix))){
            log.info("Mutant Sequence Found in diagonal");
            return true;
        }
        log.info("No Mutant Sequence Found");
        return false;
    }

    public  boolean analizeSequence(List<String> dna){
        for (String sequence: dna) {
            if(checkSequence(sequence)){
                return true;
            }
        }
        return  false;
    }

    private  boolean checkSequence(String sequence) {
        if (sequence.contains(Constants.AAAA)) {
            return true;
        } else if (sequence.contains(Constants.CCCC)) {
            return true;
        } else if (sequence.contains(Constants.GGGG)) {
            return true;
        } else return sequence.contains(Constants.TTTT);

    }

    //CHECK THAT THE DNA ONLY HAVE VALID CHARACTERS
    public boolean validateSequenceDna(List <String> dna){
        Pattern p = Pattern.compile("^[ATCG]+$");

        for (String sequence: dna) {
            Matcher m = p.matcher(sequence);
            if(!m.matches()){
                return false;
            }
        }
        return !dna.isEmpty();
    }
}
