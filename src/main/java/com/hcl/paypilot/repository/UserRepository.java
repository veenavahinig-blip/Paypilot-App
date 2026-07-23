package com.hcl.paypilot.repository;
 
import java.util.Optional;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
 
import com.hcl.paypilot.entity.UserEntity;
 
/**
* Repository interface for performing database operations on UserEntity.
*
* This interface provides CRUD operations through JpaRepository and
* custom methods for user-related queries such as email lookup and
* sequence generation.
*
* @author PayPilot Team
*/
public interface UserRepository extends JpaRepository<UserEntity, String> {
 
    /**
     * Retrieves the next value from the database sequence used
     * for generating unique user IDs.
     *
     * @return next sequence value
     */
    @Query(value = "SELECT user_seq.NEXTVAL FROM dual", nativeQuery = true)
    Long getNextSequenceValue();
 
    /**
     * Finds a user by email address.
     *
     * @param userEmail user's email address
     * @return Optional containing UserEntity if found,
     *         otherwise an empty Optional
     */
    Optional<UserEntity> findByUserEmail(String userEmail);
    
    Optional<UserEntity> findByUserId(String userId);
 
    /**
     * Checks whether a user exists with the specified email address.
     *
     * @param userEmail user's email address
     * @return true if user exists, otherwise false
     */
    boolean existsByUserEmail(String userEmail);
 
}
 