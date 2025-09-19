package ee.valiit.shroomshareback.persistence.role;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends Repository<Role, Integer> {
    @Query("select r from Role r where r.name = :roleName")
    Role getRoleByRoleName(String roleName);

}