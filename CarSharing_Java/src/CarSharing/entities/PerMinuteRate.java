package CarSharing.entities;

import CarSharing.provided.TripStatus;

public class PerMinuteRate extends Rate{

    private final int perMinute;


    public PerMinuteRate(int perMinute){
        if(perMinute < 1){
            throw new IllegalArgumentException("invalid value");
        }
        this.perMinute = perMinute;
    }

    @Override
    public int total​(Trip t) {

        if(t.getStatus() != TripStatus.COMPLETED)
            return 0;

        return  (t.duration()/60) * perMinute;
    }
}
