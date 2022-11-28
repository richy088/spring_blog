package com.codeup;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.yaml.snakeyaml.events.Event;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;
//    cannot change because final
    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @PostMapping("/post")
    public String createPosts(@ModelAttribute Post post, Model model) {
        User user = userDao.getById(1L);
        post.setUser(user);
        postDao.save(post);
        emailService.prepareAndSend(post, "Days of Week", "There are 7 days in a week");
        return "redirect:/posts";
    }

    @GetMapping("/createPost")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @GetMapping("/posts")
    public String getAllPosts(Model model) {
//        *initial posts*

//        List<Post> posts = new ArrayList<>();
//        Post post1 = new Post();
//        post1.setTitle("title1");
//        post1.setBody("body1");
//        Post post2 = new Post();
//        post2.setTitle("title2");
//        post2.setBody("body2");
//        posts.add(post1);
//        posts.add(post2);
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/post/{id}")
    public String getPostByID(@PathVariable Long id, Model model) {
        Post post = postDao.getById(id);
//        post.setTitle("title");
//        post.setBody("body");
        model.addAttribute("post", post);
        return "posts/show";
    }
    @GetMapping("/post/{id}/edit")
    public String showCreateForm(@PathVariable Long id, Model model) {
        Post post = postDao.getById(id);
        model.addAttribute("post", post);
        return "posts/edit";
    }
}
