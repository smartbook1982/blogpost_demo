package com.blog.post.demo.web;

import com.blog.post.demo.domain.Blog;
import com.blog.post.demo.processor.BlogManager;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlogPostRest {

    Gson gson = new Gson();

    @Autowired
    BlogManager manager;

    @RequestMapping (path = "/postBlog", method = RequestMethod.POST)
    public void postBlog (@RequestBody Blog blog) {
        manager.addBlog(blog);
    }

    @RequestMapping (produces = "application/json", path = "/getBlog", method = RequestMethod.GET)
    public String getBlog (@RequestParam("id") long blogId)
    {
        return gson.toJson(manager.getBlogPost(blogId)) == null ? "blog not found" : gson.toJson(manager.getBlogPost(blogId));
    }

}
