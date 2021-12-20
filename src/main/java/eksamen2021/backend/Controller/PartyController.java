package eksamen2021.backend.Controller;

import eksamen2021.backend.Model.Candidate;
import eksamen2021.backend.Model.Party;
import eksamen2021.backend.Service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
public class PartyController {

    private PartyService partyService;

    @Autowired
    public PartyController(PartyService partyService) {
        this.partyService = partyService;
    }

    @GetMapping("/party/{id}")
    public ResponseEntity<Party> readParty(@PathVariable Integer id) {
        Party party = partyService.findById(id);
        return new ResponseEntity<>(party, HttpStatus.OK);
    }

    @GetMapping("/allParties")
    public ResponseEntity<List<Party>> readAllParties() {
        List<Party> parties = partyService.findAllParty();
        partyService.calculatePartyVoteToPercentage();
        return new ResponseEntity<>(parties, HttpStatus.OK);
    }

    @GetMapping("/partyCandidates/{id}")
    public ResponseEntity<Set<Candidate>> readAllPartyCandidates(@PathVariable Integer id) {
        Set<Candidate> candidates = partyService.findAllCandidatesOnParty(id);
        return new ResponseEntity<>(candidates, HttpStatus.OK);
    }

    @PostMapping("/party")
    public ResponseEntity<Party> createParty(@RequestBody Party party) throws URISyntaxException {
        Party tmpParty = null;
        tmpParty = partyService.saveParty(party);
        return ResponseEntity.created(new URI("/party/" + tmpParty.getPartyId())).body(tmpParty);
    }

    @PutMapping("/party/{id}")
    public ResponseEntity<Party> updateParty(@PathVariable Integer id, @RequestBody Party party) {
        Party tmpParty = partyService.updateParty(party, id);
        return ResponseEntity.ok().body(tmpParty);
    }

    @DeleteMapping("/party/{id}")
    public ResponseEntity<?> deleteParty(@PathVariable Integer id) {
        partyService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
