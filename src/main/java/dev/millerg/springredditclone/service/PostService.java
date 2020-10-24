package dev.millerg.springredditclone.service;

import dev.millerg.springredditclone.dto.PostRequest;
import dev.millerg.springredditclone.dto.PostResponse;
import dev.millerg.springredditclone.exceptions.PostNotFoundException;
import dev.millerg.springredditclone.exceptions.SubredditNotFoundException;
import dev.millerg.springredditclone.mapper.PostMapper;
import dev.millerg.springredditclone.model.Post;
import dev.millerg.springredditclone.model.Subreddit;
import dev.millerg.springredditclone.model.User;
import dev.millerg.springredditclone.repository.PostRepository;
import dev.millerg.springredditclone.repository.SubredditRepository;
import dev.millerg.springredditclone.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final SubredditRepository subredditRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    public void save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
        log.info("subreddit " + subreddit);
        User currentUser = authService.getCurrentUser();
        log.info("user " + currentUser);
        postRepository.save(postMapper.map(postRequest, subreddit, currentUser));
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long id) throws SubredditNotFoundException {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SubredditNotFoundException(id.toString()));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername (String username){
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));
        log.info("user " + user);
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
}
