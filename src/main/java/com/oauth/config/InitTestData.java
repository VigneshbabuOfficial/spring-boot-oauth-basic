package com.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Component;

import com.oauth.entity.AppUser;
import com.oauth.entity.Post;
import com.oauth.repository.AppUserRepository;
import com.oauth.repository.PostRepository;

@Component
public class InitTestData implements ApplicationListener<ApplicationContextEvent> {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public void onApplicationEvent(ApplicationContextEvent applicationContextEvent) {
        postRepository.save(new Post(0, "Post title nr1", "Post content nr1"));
        postRepository.save(new Post(0, "Post title nr2", "Post content nr2"));
        postRepository.save(new Post(0, "Post title nr3", "Post content nr3"));
       System.out.println("3 posts saved.");

        appUserRepository.save(
                new AppUser("wj@prajumsook.com", "passw0rd", "USER")
        );
        System.out.println("User saved.");
    }
}
