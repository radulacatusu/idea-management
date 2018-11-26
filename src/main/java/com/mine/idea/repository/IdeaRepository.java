package com.mine.idea.repository;

import com.mine.idea.model.Idea;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @stefanl
 */
public interface IdeaRepository extends JpaRepository<Idea, Long> {
}
