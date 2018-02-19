package org.tvornica_znanosti.udruga.repository;

import org.tvornica_znanosti.udruga.domain.Member;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Member entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
