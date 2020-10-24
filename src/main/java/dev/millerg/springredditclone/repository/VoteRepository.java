package dev.millerg.springredditclone.repository;

import dev.millerg.springredditclone.model.Post;
import dev.millerg.springredditclone.model.User;
import dev.millerg.springredditclone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);

}
