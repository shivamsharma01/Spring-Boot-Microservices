package com.priso.SpringDataJPA.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.priso.SpringDataJPA.dto.User;

@Repository
//public interface UserRepository extends CrudRepository<User, Long> {
//public interface UserRepository extends PagingAndSortingRepository<User, Long> {
public interface UserRepository extends JpaRepository<User, Long> {

	@Transactional
	@Modifying
	@Query("update User set address=:address where id=:id")
	void updateAddress(@Param("id") Long id, @Param("address") String address);

	@Query("select u from User u where u.firstName=?1")
	List<User> getAllUsersByFirstName(String firstName);

	@Query("select u from User u where u.gender=:gender")
	List<User> getAllUsersByGender(@Param("gender") String gender);

	@Query("select u from User u where u.gender=:gender")
	List<User> getAllUsersByGenderAndSort(@Param("gender") String gender, Sort sortingParam);

}
