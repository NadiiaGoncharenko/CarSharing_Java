package CarSharing.entities;

import CarSharing.provided.Car;
import CarSharing.provided.Customer;
import CarSharing.provided.DateTime;
import CarSharing.provided.Location;
import CarSharing.provided.TripStatus;

/**
 * A trip in the car sharing system.<br>
 * 
 * A trip collects information about time, location, customer and car involved.
 * It also contains a rate responsible for calculation of the total amount charged for
 * a trip.
 * 
 * Some operations' behavior depends on this trip's
 * {@link CarSharing.provided.TripStatus}.
 * 
 */
public class Trip implements Comparable<Trip> {

	private final Car car;
	private double	distance;
	private final Rate rate;

	private final Customer renter;
	private Location	endLocation;
	private DateTime	endTime;

	private Location	startLocation;
	private DateTime	startTime;
	private TripStatus	status = TripStatus.CREATED;


	public Trip(Car car,
				 Customer customer,
				 Rate rate){
		if(car == null || customer == null || rate == null){
			throw new IllegalArgumentException("invalid value");
		}

		this.car=car;
		this.renter = customer;
		this.rate = rate;
	}

	public Trip(Car car,
				 Customer renter,
				 Rate rate,
				 Location startLocation,
				 DateTime startTime){

		this(car, renter, rate);
		if(startLocation == null || startTime == null){
			throw new IllegalArgumentException("invalid value");
		}
		this.startLocation = startLocation;
		this.startTime = startTime;

		this.status = TripStatus.STARTED;

	}

	public Trip(Car car,
				 Customer renter,
				 Rate rate,
				 Location startLocation,
				 DateTime startTime,
				 Location endLocation,
				 DateTime endTime,
				 double distance){

		this(car, renter, rate);
		if(startLocation == null || startTime == null ||endLocation == null || endTime == null ||distance < 0.1){
			throw new IllegalArgumentException("invalid value");
		}
		this.startLocation = startLocation;
		this.startTime = startTime;

		this.endLocation = endLocation;
		this.endTime = endTime;
		this.distance = distance;

		this.status = TripStatus.COMPLETED;

	}


	public Trip(Trip tr){

		this.car = new Car(tr.car);
		this.renter = new Customer(tr.renter);

		this.startLocation = new Location(tr.startLocation);
		this.startTime = new DateTime(tr.startTime);

		this.endLocation = new Location(tr.endLocation);
		this.endTime = new DateTime(tr.endTime);

		this.distance = tr.distance;
		this.rate = tr.rate;
		this.status = tr.status;

	}

	public Trip start(Location startLocation, DateTime startTime){

		if(startLocation == null || startTime == null){
			throw new IllegalArgumentException("invalid entry");
		}
		this.startTime = startTime;
		this.startLocation = startLocation;
		if(status == TripStatus.COMPLETED || status == TripStatus.STARTED ){
			throw new IllegalStateException("not possible to start this trip");
		}
		status = TripStatus.STARTED;

		return this;
	}

	public Trip end(Location endLocation, DateTime endTime, double distance){
		if(endLocation == null || endTime == null || distance < 0.1){
			throw new IllegalArgumentException("invalid entry");
		}
		this.endTime = endTime;
		this.endLocation = endLocation;
		if(status == TripStatus.COMPLETED || status != TripStatus.STARTED ){
			throw new IllegalStateException("not possible to complete this trip");
		}
		this.distance = distance;
		status = TripStatus.COMPLETED;

		return this;
	}

	public final int total(){
		return rate.total​(this);
	}

	@Override
	public int compareTo(Trip o) {

		if (this.getStatus() == TripStatus.STARTED && o.getStatus() == TripStatus.STARTED) {
			this.startTime.compareTo(o.startTime);
		}

		if(this.getStatus() == TripStatus.CREATED || o.getStatus() == TripStatus.CREATED)
			return Integer.MAX_VALUE;

		return 0;
	}


	@Override
	public String toString() {
		return "Trip{" +
				"car=" + car.toString() +
				", distance=" + getDistance() +
				", rate=" + getRate() +
				", renter=" + renter.toString() +
				", endLocation=" + getEndLocation() +
				", endTime=" + getEndTime() +
				", startLocation=" + getStartLocation() +
				", startTime=" + getStartTime() +
				", status=" + getStatus() +
				'}';
	}

	/**
	 * The duration of this trip in seconds.<br>
	 * 
	 * 
	 * 
	 * @ProgrammingProblem.Hint use {@link DateTime#diff(DateTime)}
	 * @return the difference in seconds if this trip is completed, zero otherwise
	 */
	public int duration() {
		if (status == TripStatus.COMPLETED)
			return (int) (startTime.diff(endTime));

		return 0;
	}


	public double getDistance() {
		return distance;
	}

	public Rate getRate() {
		return rate;
	}

	public Location getEndLocation() {
		return endLocation;
	}

	public DateTime getEndTime() {
		return endTime;
	}

	public Location getStartLocation() {
		return startLocation;
	}

	public DateTime getStartTime() {
		return startTime;
	}

	public TripStatus getStatus() {
		return status;
	}
}
