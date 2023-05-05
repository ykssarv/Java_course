package ee.taltech.iti0202.socialnetwork.message;

import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

public class MessagesTest {
    Message message;
    User user;
    Set<Message> messages;

    @BeforeEach
    void setUp() {
        user = new User("Yuri", 67);
        messages = new HashSet<>();
        message = new Message("tere","head aega", user);
    }
    @Test
    void testGetUser(){
        assertEquals(user, message.getAuthor());
    }
    @Test
    void testGetTitle(){
        assertEquals("tere", message.getTitle());
    }
    @Test
    void testGetMessage(){
        assertEquals("head aega", message.getMessage());
    }
}
