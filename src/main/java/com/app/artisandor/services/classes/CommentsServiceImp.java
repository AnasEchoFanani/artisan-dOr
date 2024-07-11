package com.app.artisandor.services.classes;

import com.app.artisandor.entity.Comments;
import com.app.artisandor.entity.Product;
import com.app.artisandor.exception.CommentsNotFoundException;
import com.app.artisandor.repository.CommentsRepository;
import com.app.artisandor.services.interfaces.CommentsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentsServiceImp implements CommentsService {

    private static final Logger logger = LoggerFactory.getLogger(CommentsServiceImp.class);

    private final CommentsRepository commentsRepository;

    @Autowired
    public CommentsServiceImp(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    @Override
    public Page<Comments> getComments(Pageable pageable) {
        logger.info("Fetching comments with pageable: {}", pageable);
        return commentsRepository.findAll(pageable);
    }

    @Override
    public Comments getCommentsById(Long id) {
        logger.info("Fetching comment with id: {}", id);
        return commentsRepository.findById(id)
                .orElseThrow(() -> new CommentsNotFoundException("Comment not found for id: " + id));
    }

    @Override
    @Transactional
    public Comments saveComments(Comments comments) {
        logger.info("Saving comment: {}", comments);
        try {
            return commentsRepository.save(comments);
        } catch (Exception e) {
            logger.error("Error saving comment: {}", comments, e);
            throw new RuntimeException("Failed to save comment", e);
        }
    }

    @Override
    @Transactional
    public void deleteCommentsById(Long id) {
        logger.info("Deleting comment with id: {}", id);
        try {
            commentsRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error deleting comment with id: {}", id, e);
            throw new RuntimeException("Failed to delete comment", e);
        }
    }

    @Override
    public List<Comments> getCommentsByProduct(Product product) {
        logger.info("Fetching comments for product: {}", product);
        return commentsRepository.findByProduct(product);
    }
}
