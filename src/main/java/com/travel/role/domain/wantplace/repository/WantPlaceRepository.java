package com.travel.role.domain.wantplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.travel.role.domain.wantplace.entity.WantPlace;

public interface WantPlaceRepository extends JpaRepository<WantPlace, Long> {
	@Query(value = "SELECT DISTINCT w " +
		"FROM WantPlace w " +
		"JOIN FETCH w.room r " +
		"WHERE r.id = :roomId ORDER BY w.createDate ASC")
	List<WantPlace> findWantPlaceByRoomId(@Param("roomId") Long roomId);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM WantPlace w where w.id = :placeId ")
	void deleteWantPlaceById(@Param("placeId") Long placeId);
}
