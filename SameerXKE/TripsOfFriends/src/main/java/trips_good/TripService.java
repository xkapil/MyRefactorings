package trips_good;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    private TripDao dao;

    public TripService(TripDao dao) {
        this.dao = dao;
    }

    public List<Trip> getUserTrips(User user, User loggedInUser) throws NotLoggedInException {
        if (loggedInUser == null) {
            throw new NotLoggedInException("You are not currently logged in..");
        }
        return user.isFriend(loggedInUser) ? dao.tripsBy(user) : new ArrayList<Trip>();
    }

}
