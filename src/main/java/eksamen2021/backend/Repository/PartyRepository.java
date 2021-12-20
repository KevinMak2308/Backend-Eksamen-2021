package eksamen2021.backend.Repository;


import eksamen2021.backend.Model.Party;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Qualifier("Party")
@Repository
public interface PartyRepository extends JpaRepository<Party, Integer> {
}
