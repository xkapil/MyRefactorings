package trips_good;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by kapil on 2/29/16.
 */
public class TripServiceTest {

    private User loggedInUser;

    private static final User GUEST = null;

    private static final User ME = new User.Builder().build();

    private static final User USER1 = new User.Builder().build();
    private static final User USER2 = new User.Builder().build();
    private static final User USER3 = new User.Builder().build();

    private static final Trip TO_SIKKIM = new Trip();
    private static final Trip TO_LADDAKH = new Trip();
    private TripService tripService;

    @Before
    public void init() {
        tripService = new TripService(new TripDao(){
            @Override
            public List<Trip> tripsBy(User user) {
                return user.getTrips();
            }
        });
    }

    @Test(expected = NotLoggedInException.class)
    public void should_ThrowNotLoggedInException_when_UserIsNotLoggedIn() {
        loggedInUser = GUEST;
        tripService.getUserTrips(USER1, loggedInUser);
    }

    @Test
    public void should_ReturnEmpty_When_ThereAreNoFriends() {
        loggedInUser = ME;
        User friend = new User.Builder().withTrips(TO_LADDAKH).build();
        Assert.assertEquals(0, tripService.getUserTrips(friend, loggedInUser).size());
    }

    @Test
    public void should_ReturnTrips_When_UserIsAFriend() {
        loggedInUser = ME;
        User friend = new User.Builder().withTrips(TO_LADDAKH).withFriends(ME).build();
        Assert.assertEquals(1, tripService.getUserTrips(friend, loggedInUser).size());
    }

    @Test
    public void should_ReturnZeroTrips_When_UserIsAFriendAndThereAreNoTrips() {
        loggedInUser = ME;
        User friend = new User.Builder().withFriends(ME).build();
        Assert.assertEquals(0, tripService.getUserTrips(friend, loggedInUser).size());
    }

    @Test
    public void should_ReturnMultipleTrips_When_UserIsAFriendAndThereAreMultipleTrips() {
        loggedInUser = ME;
        User friend = new User.Builder().withFriends(ME).withTrips(TO_LADDAKH, TO_SIKKIM).build();
        Assert.assertEquals(2, tripService.getUserTrips(friend, loggedInUser).size());
    }

}