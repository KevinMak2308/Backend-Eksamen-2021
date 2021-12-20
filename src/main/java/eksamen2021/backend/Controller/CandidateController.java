package eksamen2021.backend.Controller;

import eksamen2021.backend.Model.Candidate;
import eksamen2021.backend.Service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@CrossOrigin
public class CandidateController {

    private CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping("/candidate/{id}")
    public ResponseEntity<Candidate> readCandidate(@PathVariable Integer id) {
        Candidate candidate = candidateService.findById(id);
        return new ResponseEntity<>(candidate, HttpStatus.OK);
    }

    @GetMapping("/allCandidates")
    public ResponseEntity<List<Candidate>> readAllCandidates() {
        List<Candidate> candidates = candidateService.findAllCandidate();
        return new ResponseEntity<>(candidates, HttpStatus.OK);
    }

    @PostMapping("/candidate")
    public ResponseEntity<Candidate> createCandidate(@RequestBody Candidate candidate) throws URISyntaxException {
        Candidate tmpCandidate = null;
        tmpCandidate = candidateService.saveCandidate(candidate);
        return ResponseEntity.created(new URI("/candidate" + tmpCandidate.getCandidateId())).body(tmpCandidate);
    }

    @PutMapping("/candidate/{id}")
    public ResponseEntity<Candidate> updateCandidate(@PathVariable Integer id, @RequestBody Candidate candidate) {
        Candidate tmpCandidate = candidateService.updateCandidate(candidate, id);
        return ResponseEntity.ok().body(tmpCandidate);
    }

    @DeleteMapping("/candidate/{id}")
    public ResponseEntity<?> deleteCandidate(@PathVariable Integer id) {
        candidateService.deleteCandidate(id);
        return ResponseEntity.ok().build();
    }
}
