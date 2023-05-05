package ee.taltech.iti0202.socialnetwork;

import ee.taltech.iti0202.socialnetwork.feed.Feed;
import ee.taltech.iti0202.socialnetwork.group.Group;
import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

public class SocialNetworkTest {
    SocialNetwork socialNetwork;
    User user;
    Set<Message> messages;

    @BeforeEach
    void setUp() {
        user = new User("Yuri", 67);
        messages = new HashSet<>();
        socialNetwork = new SocialNetwork();

    }
    @Test
    void testRegisterGroups(){
        Group group = new Group("tere", user);
        socialNetwork.registerGroup(group);
        Set<Group> groups = socialNetwork.getGroups();
        assertEquals(1, groups.size());
    }
    @Test
    void testGetFeedForUser(){
        Group group = new Group("tere", user);
        Message message = new Message("joujou", "hai", user);
        group.publishMessage(message);
        socialNetwork.registerGroup(group);
        Feed feed = socialNetwork.getFeedForUser(user);

        assertEquals(0, feed.getMessages().size());
    }
}