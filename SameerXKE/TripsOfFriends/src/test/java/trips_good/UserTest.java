package trips_good;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kapil on 3/1/16.
 */
public class UserTest {

    private final static User USER1 = new User.Builder().build();
    private final static User USER2 = new User.Builder().build();

    @Test
    public void should_returnTrue_when_UserIsAFriend(){
        User user = new User.Builder().withFriends(USER1).build();
        Assert.assertTrue(user.isFriend(USER1));
    }

    @Test
    public void should_returnFalse_when_UserIsNotAFriend(){
        User user = new User.Builder().build();
        Assert.assertFalse(user.isFriend(USER1));
    }
}