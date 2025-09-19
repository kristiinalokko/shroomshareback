package ee.valiit.shroomshareback.persistence.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("select r from Role r where r.name = :name")
    Role getRoleByRoleName(String name);


}