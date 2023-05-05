package ee.taltech.iti0202.socialnetwork.feed;

import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

public class FeedTest {
    Feed feed;
    User user;
    Set<Message> messages;

    @BeforeEach
    void setUp() {
        user = new User("Yuri", 67);
        messages = new HashSet<>();
        feed = new Feed(user, messages);

    }
    @Test
    void testGetUser(){
        assertEquals(user, feed.getUser());
    }
    @Test
    void testGetMessages(){
        assertEquals(messages, feed.getMessages());
    }
}
