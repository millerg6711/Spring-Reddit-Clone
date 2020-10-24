package dev.millerg.springredditclone.repository;

import dev.millerg.springredditclone.model.Post;
import dev.millerg.springredditclone.model.Subreddit;
import dev.millerg.springredditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}
