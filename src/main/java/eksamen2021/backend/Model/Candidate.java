package eksamen2021.backend.Model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "candidate")
public class Candidate {

    @Id
    @Column(name = "candidate_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer candidateId;

    @Column(name = "candidate_name")
    private String candidateName;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "party_id")
    private Party party;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return candidateId.equals(candidate.candidateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateId);
    }
}
