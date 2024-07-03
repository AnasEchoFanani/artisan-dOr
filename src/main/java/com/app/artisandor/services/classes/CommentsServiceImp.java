package com.app.artisandor.services.classes;

import com.app.artisandor.entity.Comments;
import com.app.artisandor.services.interfaces.CommentsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CommentsServiceImp implements CommentsService {
    @Override
    public Page<Comments> getComments(Pageable pageable) {
        return null;
    }

    @Override
    public Comments getCommentsById(Long id) {
        return null;
    }

    @Override
    public Comments saveComments(Comments comments) {
        return null;
    }

    @Override
    public void deleteCommentsById(Long id) {

    }
}
