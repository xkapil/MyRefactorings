package trips_good;

import java.util.ArrayList;
import java.util.List;

public class User {

    private List<User> friends;

    private List<Trip> trips;

    public List<Trip> getTrips() {
        return trips;
    }

    public boolean isFriend(User user){
        return friends.contains(user);
    }

    private User(List<User> friends, List<Trip> trips) {
        this.friends = friends;
        this.trips = trips;
    }

    public static class Builder {

        private List<User> friends = new ArrayList<User>();

        private List<Trip> trips = new ArrayList<Trip>();

        public Builder withFriends(User... users) {
            for (User user :
                    users) {
                friends.add(user);
            }
            return this;
        }

        public Builder withTrips(Trip... trips) {
            for (Trip trip :
                    trips) {
                this.trips.add(trip);
            }
            return this;
        }

        public User build() {
            return new User(this.friends, this.trips);
        }
    }
}