package com.mine.idea.util;

import com.mine.idea.api.IdeaApi;
import com.mine.idea.model.Idea;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * Misc conversion utils for Idea API entities
 */
@Component
public class IdeaConverter {

    /**
     * Converts an Idea from the db to the internal IdeaApi
     */
    public IdeaApi toDTO(Idea dto) {
        IdeaApi idea;
        if (dto == null) {
            return null;
        }
        BeanUtils.copyProperties(dto, idea = new IdeaApi());
        idea.setCreatedAt(dto.getCreatedAt().getTime());
        return idea;
    }

    /**
     * Converts an internal IdeaAPI to Idea from the db
     */
    public Idea fromDTO(IdeaApi dto) {
        Idea idea;
        if (dto == null) {
            return null;
        }
        BeanUtils.copyProperties(dto, idea = new Idea());

        return idea;
    }
}
