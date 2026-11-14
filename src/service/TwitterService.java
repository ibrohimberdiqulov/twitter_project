package service;

import dao.TwitterDao;
import entity.Post;
import entity.Profile;
import entity.View;
import enums.Role;
import enums.Status;
import utils.Util;

import java.util.ArrayList;
import java.util.List;

public class TwitterService {

    private final TwitterDao twitterDao = new TwitterDao();

    public boolean register(String fullName, String username, String password) {
        Profile test = twitterDao.getProfileByUsrename((username));
        if (test != null) return false;
        Profile profile = new Profile(Util.userId++, fullName, username, password, Role.USER, Status.ACTIVE);
        twitterDao.saveProfile(profile);
        return true;
    }

    public Role login(String username, String password) {

        Profile profile = twitterDao.getProfileByUsrename(username);

        if (profile == null) return null;

        if (!profile.getPassword().equals(password)) return null;

        Util.currentUserId = profile.getId();

        return profile.getRole();
    }


    public boolean createPost(String title, String content) {
        if (title == null) return false;
        if (title.isBlank()) return false;

        Profile profile = twitterDao.getProfileById(Util.currentUserId);
        if (profile.getStatus().equals(Status.BLOCK)) return false;


        Post post = new Post(Util.postId++, title, content, Util.currentUserId);
        twitterDao.savePost(post);
        return true;
    }


    public List<Post> getPostsByUserId(int userId) {
        List<Post> list = twitterDao.getAllPosts();
        List<Post> resList = new ArrayList<>();
        for (Post post : list) {
            if (post.getUserId() == userId) resList.add(post);
        }
        return resList;
    }

    public List<Post> getAllPosts() {
        return twitterDao.getAllPosts();
    }

    public List<Profile> getAllUsers() {
        return twitterDao.getAllUsers();
    }

    public void blockUserById(int id) {
        List<Profile> profiles = twitterDao.getAllUsers();
        for (Profile profile : profiles) {
            if (profile.getId() == id) {
                profile.setStatus(Status.BLOCK);
                break;
            }
        }
    }

    public void createView(int userId, int postId){
        View view = new View( userId, postId);
        twitterDao.saveView(view);
    }

/*
    public Post getPostsById(int userId, int postId) {
        Post post = new Post(userId, postId);
        TwitterService.
    }*/
}