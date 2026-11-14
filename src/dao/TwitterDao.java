package dao;

import entity.Post;
import entity.Profile;
import entity.View;
import enums.Role;
import enums.Status;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TwitterDao {

    private List<Profile> profileList = new ArrayList<>();

    private List<Post> postList = new LinkedList<>();

    private List<View> viewList = new ArrayList<>();



    {
        profileList.add(new Profile(1, "Ibrohim Berdiqulov", "ibrohim", "123", Role.ADMIN,Status.ACTIVE));
    }

    public void saveProfile(Profile profile){
        profileList.add(profile);
    }

    public void savePost( Post post){
        postList.add(post);
    }

    public void saveView (View view){
        viewList.add(view);
    }

    public List<Profile> getAllUsers(){
        return profileList;
    }

    public Profile getProfileByUsrename( String username) {
        for (Profile p : profileList) {
            if (p.getUsername().equals(username)) {
                return p;
            }
        }
        return null;
    }


    public Profile getProfileById( int id) {
        for (Profile p : profileList) {
            if (p.getId() == (id)) {
                return p;
            }
        }
        return null;
    }

    public List<Post> getAllPosts() {
        return postList;
    }
}
