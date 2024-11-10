package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }

    public Optional<BlogPost> getPostById(Long id) {
        return blogPostRepository.findById(id);
    }

    public BlogPost createPost(BlogPost post) {
        return blogPostRepository.save(post);
    }

    public BlogPost updatePost(Long id, BlogPost post) {
        post.setId(id);
        return blogPostRepository.save(post);
    }

    public void deletePost(Long id) {
        blogPostRepository.deleteById(id);
    }
}
