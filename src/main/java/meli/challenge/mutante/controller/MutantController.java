package meli.challenge.mutante.controller;

import meli.challenge.mutante.Model.MutantRequest;
import meli.challenge.mutante.component.MutantAnalizer;
import meli.challenge.mutante.constants.Constants;
import meli.challenge.mutante.persistence.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class MutantController {

    @Autowired
    MutantAnalizer mutantAnalizer;

    @PostMapping("/mutant")
    public ResponseEntity<?> isMutant(@RequestBody MutantRequest mutantRequest)
     {
            if(!mutantAnalizer.validateSequenceDna(mutantRequest.getDna())){
                return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);

            }
            if(mutantAnalizer.isMutant(mutantRequest.getDna())){
                RedisManager.getInstance().counter(Constants.MUTANT);
                return new ResponseEntity<>("", HttpStatus.OK);
            }
         RedisManager.getInstance().counter(Constants.HUMAN);
         return new ResponseEntity<>("", HttpStatus.FORBIDDEN);

     }

     @GetMapping("/stats")
     public ResponseEntity<?> stats(){


         var body = new HashMap<String,Float>();
         var mutantCount = Float.valueOf(RedisManager.getInstance().getByKey(Constants.MUTANT));
         var humanCount = Float.valueOf(RedisManager.getInstance().getByKey(Constants.HUMAN));

         body.put("count_mutant_dna", mutantCount);
         body.put("count_human_dna",humanCount);
         body.put("ratio",mutantCount/humanCount);
         return new ResponseEntity<>(body, HttpStatus.OK);

     }


}
