package site.project.accountinfoapp.service.kftcAuth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.project.accountinfoapp.service.kftcAuth.domain.KftcAuthorization;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorizationRepository extends JpaRepository<KftcAuthorization, UUID> {

    Optional<KftcAuthorization> findByUsername(String username);
}
