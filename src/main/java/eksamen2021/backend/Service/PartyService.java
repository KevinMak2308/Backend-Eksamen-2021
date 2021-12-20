package eksamen2021.backend.Service;

import eksamen2021.backend.Model.Candidate;
import eksamen2021.backend.Model.Party;
import eksamen2021.backend.Repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Set;

@Service
public class PartyService {

    private PartyRepository partyRepository;

    @Autowired
    public PartyService(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }


    public Party findById(Integer id) {
        return partyRepository.findById(id).orElseThrow(() -> new NoResultException("Party with id: " + id + " does not exist!"));
    }

    public List<Party> findAllParty(){

        return partyRepository.findAll();
    }

    public Party saveParty(Party party){

        return partyRepository.save(party);
    }

    public Party updateParty(Party party, Integer id) {
        Party tmpParty = partyRepository.findById(id).orElseThrow(() -> new NoResultException("Party with id: " + id + " does not exist!"));
        tmpParty.setPartyId(party.getPartyId());
        tmpParty.setPartyName(party.getPartyName());
        tmpParty.setPartyVote(party.getPartyVote());
        return partyRepository.save(tmpParty);
    }

    public void deleteById(Integer id){

        partyRepository.deleteById(id);
    }

    public Set<Candidate> findAllCandidatesOnParty(Integer id){
        Party tmpParty = partyRepository.findById(id).orElseThrow(()-> new NoResultException("Party with id: " + id + " was not found"));
        Set<Candidate> candidates = tmpParty.getCandidateSet();
        return candidates;
    }

    public void calculatePartyVoteToPercentage() {
        List<Party> partyList = partyRepository.findAll();
        Integer allVotes = 2568;

        for (int i = 0; i < partyList.size() ; i++) {
            Party tmpParty = partyList.get(i);

            Double vote = tmpParty.getPartyVote();

            Double votePercentage = vote / allVotes * 100;

            tmpParty.setPartyVotePercentage(votePercentage);

            partyRepository.save(tmpParty);
        }


    }
}
