/*
 * AuthenticatedConsumerRepository.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedTaskRepository extends AbstractRepository {

	@Query("select ct from Task ct where ct.endMoment < current_timestamp and ct.isPublic = true")
	Collection<Task> findMany();

	@Query("select t from Task t where t.id = :id")
	Task findOneTaskById(@Param("id") int id);

	@Query("select ct from Task ct where ct.endMoment > current_timestamp and ct.isPublic = true")
	Collection<Task> findNonFinishedTask();
}
