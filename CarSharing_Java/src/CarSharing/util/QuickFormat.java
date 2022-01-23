package CarSharing.util;

import CarSharing.entities.Trip;
import CarSharing.provided.Formatter;

public class QuickFormat extends Formatter<Trip> {


    public QuickFormat(){}

    @Override
    public String format(Trip trip) {

        return   String.format("%8d min %5.1f km %8d.%02d EUR", trip.duration()/60, trip.getDistance(), trip.total()/100, trip.total()%100);

    }
}
