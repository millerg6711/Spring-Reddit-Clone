package dev.millerg.springredditclone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostRequest {

    private Long postId;

    private String subredditName;

    private String postName;

    private String url;

    private String description;

}
