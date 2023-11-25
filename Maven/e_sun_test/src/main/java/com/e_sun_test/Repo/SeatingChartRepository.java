package com.e_sun_test.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.e_sun_test.model.SeatingChart;

public interface SeatingChartRepository extends JpaRepository<SeatingChart, Integer> {

	@Procedure(name="showAllChart")
	public List<Object[]> findAllChart();
}
