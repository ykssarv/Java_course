package ee.taltech.iti0202.socialnetwork;
import ee.taltech.iti0202.socialnetwork.feed.Feed;
import ee.taltech.iti0202.socialnetwork.group.Group;
import ee.taltech.iti0202.socialnetwork.user.User;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SocialNetwork {
    private Set<Group> groups = new HashSet<>();

    public void registerGroup(Group group) {
        groups.add(group);
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public Feed getFeedForUser(User user) {
        return new Feed(
            user,
            groups.stream()
                .filter(x -> x.getParticipants().contains(user))
                .flatMap(x -> x.getMessages().stream())
                .collect(Collectors.toSet())
        );
    }
}