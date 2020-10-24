package dev.millerg.springredditclone.repository;

import dev.millerg.springredditclone.model.Comment;
import dev.millerg.springredditclone.model.Post;
import dev.millerg.springredditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
