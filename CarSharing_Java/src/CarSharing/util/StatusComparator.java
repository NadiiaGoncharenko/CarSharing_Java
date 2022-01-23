package CarSharing.util;

import       CarSharing.entities.Trip;
import CarSharing.provided.TripStatus;

import java.util.Comparator;



public class StatusComparator implements Comparator<Trip>{

    StatusComparator(){}

    @Override
    public int compare(Trip o1, Trip o2) {
        return o1.getStatus().compareTo(o2.getStatus());
    }
}
