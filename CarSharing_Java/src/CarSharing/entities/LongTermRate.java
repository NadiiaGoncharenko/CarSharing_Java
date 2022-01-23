package CarSharing.entities;

import CarSharing.provided.TripStatus;

public class LongTermRate  extends Rate{

    private int baseDuration = 5;

    private int baseAmount;


    private int perDay;

    public LongTermRate(int baseAmount,
                         int perDay){

        if( baseAmount >= 1 || perDay >= 1){
            this.baseAmount = baseAmount;
            this.perDay = perDay;
        }

    }

    @Override
    public int total​(Trip t) {

        if(t.getStatus() != TripStatus.COMPLETED)
            return 0;

        int diff = baseAmount - baseDuration;
        int summ = diff * perDay;

        return baseAmount + summ ;
    }
}
