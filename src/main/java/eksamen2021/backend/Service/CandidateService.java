package eksamen2021.backend.Service;


import eksamen2021.backend.Model.Candidate;
import eksamen2021.backend.Repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class CandidateService {

    private CandidateRepository candidateRepository;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Candidate findById(Integer id) {
       return candidateRepository.findById(id).orElseThrow(() -> new NoResultException("Candidate with id: " + id + " does not exist!"));
    }

    public List<Candidate> findAllCandidate(){

        return candidateRepository.findAll();
    }

    public Candidate saveCandidate(Candidate candidate){

        return candidateRepository.save(candidate);
    }

    public Candidate updateCandidate(Candidate candidate, Integer id) {
        Candidate tmpCandidate = candidateRepository.findById(id).orElseThrow(() -> new NoResultException("Candidate with id: " + id + " does not exist!"));
        tmpCandidate.setCandidateId(candidate.getCandidateId());
        tmpCandidate.setCandidateName(candidate.getCandidateName());
        tmpCandidate.setParty(candidate.getParty());

        return candidateRepository.save(tmpCandidate);
    }

    public void deleteCandidate(Integer id){

        candidateRepository.deleteById(id);
    }


}
