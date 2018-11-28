package com.mine.idea.repository;

import com.mine.idea.model.Idea;
import com.mine.idea.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @stefanl
 */
public interface IdeaRepository extends PagingAndSortingRepository<Idea, Long> {
    /**
     * @param name
     * @return
     */
    @Query("SELECT t FROM Idea t WHERE t.user = ?1 order by t.createdAt desc")
    List<Idea> findByUser(User name, Pageable pageable);
}
