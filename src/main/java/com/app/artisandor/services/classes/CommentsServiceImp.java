package com.app.artisandor.services.classes;

import com.app.artisandor.entity.Comments;
import com.app.artisandor.entity.Product;
import com.app.artisandor.repository.CommentsRepository;
import com.app.artisandor.services.interfaces.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsServiceImp implements CommentsService {

    private final CommentsRepository commentsRepository;

    @Autowired
    public CommentsServiceImp(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    @Override
    public Page<Comments> getComments(Pageable pageable) {
        return commentsRepository.findAll(pageable);
    }

    @Override
    public Comments getCommentsById(Long id) {
        return commentsRepository.findById(id).orElse(null);
    }

    @Override
    public Comments saveComments(Comments comments) {
        return commentsRepository.save(comments);
    }

    @Override
    public void deleteCommentsById(Long id) {
        commentsRepository.deleteById(id);
    }

    @Override
    public List<Comments> getCommentsByProduct(Product product) {
        return commentsRepository.findByProduct(product);
    }
}
