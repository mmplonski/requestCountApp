package pl.marcin.plonski.requestcountapp.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.marcin.plonski.requestcountapp.users.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
