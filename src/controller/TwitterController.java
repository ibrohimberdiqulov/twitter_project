package controller;


import entity.Post;
import entity.Profile;
import enums.Role;
import service.TwitterService;


import java.util.List;

import static utils.Util.*;



public class TwitterController {

    private final TwitterService twitterService = new TwitterService();

    public void start() {

        while (true) {
            System.out.println("""
                    1.Login
                    2.Register
                    3.Exit""");
            int menu = inputInt("Choouse one");
            switch (menu) {
                case 1 -> login();
                case 2 -> register();
                case 0 -> {
                    return;
                }
            }
        }


    }


    private void login() {
        String username = inputStr("Enter your username");
        String password = inputStr("Enter your password");

        Role role = twitterService.login(username, password);

        if (role == null) System.out.println("Username or password wrong try again");
        else if (role.equals(Role.ADMIN)) adminMenu();
        else if (role.equals(Role.USER)) userMenu();

    }


    private void register() {
        String fullName = inputStr("Enter your fullname");
        String username = inputStr("Enter your username");
        String password = inputStr("Enter your password");
        boolean res = twitterService.register(fullName, username, password);
        if (res) System.out.println("Succesfully registered");
        else System.out.println("Failed to registired");
    }

    //----------------------User Menu----------------------------
    public void userMenu() {
        while (true) {
            System.out.println("""
                    1. Add post
                    2. Show posts
                    3. Delete post
                    4. Show global posts
                    5. Change password
                    0. Log out""");
            int menu = inputInt("Chouse one");
            switch (menu) {
                case 1 -> addPost();
                case 2 -> showPosts();
                case 4 -> showGlobalPosts();
                case 0 -> {
                    return;
                }
            }
        }
    }

    private void addPost() {
        String title = inputStr("Enter post title");
        String content = inputStr("Enter post content");
        boolean res = twitterService.createPost(title, content);
        if (res) System.out.println("Successfully added post");
        else System.out.println("Failed to add post");
    }

    public void showPosts() {
        List<Post> posts = twitterService.getPostsByUserId(currentUserId);
        for (Post post : posts) {
            System.out.println(post);
        }
    }

    public void showGlobalPosts() {
        List<Post> posts = twitterService.getAllPosts();
        for (Post post : posts) {
            System.out.println(post);
        }
    }

/*
    public void postInfo( List<Post> posts ){
        for (Post post:posts){
            System.out.println(post);
        }
        int id = inputInt("Chouse one by id");
        Post post = twitterService.getPostsById( id );
        if ( post == null) {
            System.out.println("Post not found");
            return;
        }
    }
*/

    //---------------------- Admin Menu ----------------------------
    public void adminMenu() {
        while (true) {
            System.out.println("""
                    1. Show all users
                    2. Block user
                    3. Active user
                    4. Delete user
                    5. Change password
                    0. Log out""");
            int menu = inputInt("Chouse one");
            switch (menu) {
                case 1 -> showAllUsers();
                case 2 -> blockUser();
                case 0 -> {return;}
            }
        }
    }

    private void showAllUsers() {
        List<Profile> profiles = twitterService.getAllUsers();
        for (Profile profile : profiles) {
            System.out.println(profile);
        }
    }

    private void blockUser() {
        showAllUsers();
        int id = inputInt("Chouse id");
        twitterService.blockUserById(id);
        System.out.println("User Blocked");



    }
}