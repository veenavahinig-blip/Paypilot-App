package com.hcl.paypilot.repository;


import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;


import com.hcl.paypilot.entity.UserEntity;


/**

* ============================================================================

* User Repository

* ============================================================================

*

* Repository interface responsible for performing database operations

* related to user management within the PayPilot Application.

*

* This repository extends JpaRepository and provides:

* - Standard CRUD Operations

* - User Registration Support

* - User Authentication Support

* - Email Validation

* - User Lookup Operations

* - User ID Sequence Generation

*

* Entity:

* UserEntity

*

* Primary Key:

* String (userId)

*

* Features Supported:

* - User Registration

* - Login Authentication

* - OTP Verification

* - Forgot Password

* - Password Reset

* - Profile Management

* - Dashboard Retrieval

*

* Author: PayPilot Team

* ============================================================================

*/

public interface UserRepository

        extends JpaRepository<UserEntity, String> {


    /**

     * =========================================================================

     * Get Next User Sequence Value

     * =========================================================================

     *

     * Retrieves the next value from the Oracle database sequence

     * used for generating unique user identifiers.

     *

     * Sequence Name:

     * USER_SEQ

     *

     * Example:

     * USER_SEQ.NEXTVAL = 1001

     *

     * Generated User ID:

     * USER1001

     *

     * @return Next sequence value

     */

    @Query(

            value = "SELECT user_seq.NEXTVAL FROM dual",

            nativeQuery = true)

    Long getNextSequenceValue();


    /**

     * =========================================================================

     * Find User By Email

     * =========================================================================

     *

     * Retrieves a user based on the registered email address.

     *

     * Common Usage:

     * - Login

     * - Registration Validation

     * - Forgot Password

     * - OTP Verification

     * - Profile Operations

     *

     * Example:

     * user@gmail.com

     *

     * @param userEmail User Email Address

     * @return Optional containing UserEntity if found,

     *         otherwise Optional.empty()

     */

    Optional<UserEntity> findByUserEmail(

            String userEmail);


    /**

     * =========================================================================

     * Find User By User Id

     * =========================================================================

     *

     * Retrieves a user using the unique user identifier.

     *

     * Example:

     * USER1001

     *

     * Common Usage:

     * - Dashboard Retrieval

     * - Profile Management

     * - User Validation

     *

     * @param userId User Identifier

     * @return Optional containing UserEntity if found,

     *         otherwise Optional.empty()

     */

    Optional<UserEntity> findByUserId(

            String userId);


    /**

     * =========================================================================

     * Check User Existence By Email

     * =========================================================================

     *

     * Verifies whether a user already exists

     * with the specified email address.

     *

     * Common Usage:

     * - Registration Validation

     * - Duplicate Email Prevention

     * - User Verification

     *

     * Example:

     * user@gmail.com

     *

     * Returns:

     * true  -> User exists

     * false -> User does not exist

     *

     * @param userEmail User Email Address

     * @return User existence status

     */

    boolean existsByUserEmail(

            String userEmail);


}
 