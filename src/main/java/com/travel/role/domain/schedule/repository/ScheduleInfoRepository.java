package com.travel.role.domain.schedule.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.travel.role.domain.schedule.entity.ScheduleInfo;

public interface ScheduleInfoRepository extends JpaRepository<ScheduleInfo, Long> {

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM ScheduleInfo s WHERE s.id IN :ids")
	void deleteAllByIds(@Param("ids") List<Long> ids);
}
