package com.mine.idea.util;

import com.mine.idea.api.IdeaApi;
import com.mine.idea.api.UserApi;
import com.mine.idea.model.Idea;
import com.mine.idea.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * Misc conversion utils for User API entities
 */
@Component
public class UserConverter {

    /**
     * Converts an User from the db to the internal UserApi
     */
    public UserApi toDTO(User dto) {
        UserApi user;
        if (dto == null) {
            return null;
        }
        BeanUtils.copyProperties(dto, user = new UserApi());
        return user;
    }

    /**
     * Converts an internal UserApi to User from the db
     */
    public User fromDTO(UserApi dto) {
        User user;
        if (dto == null) {
            return null;
        }
        BeanUtils.copyProperties(dto, user = new User());

        return user;
    }
}
