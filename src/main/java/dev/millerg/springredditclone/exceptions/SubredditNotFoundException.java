package dev.millerg.springredditclone.exceptions;

public class SubredditNotFoundException extends RuntimeException {
    public SubredditNotFoundException(String subredditName) {
        super(subredditName);
    }
}
