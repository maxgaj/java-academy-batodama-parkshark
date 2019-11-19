package be.cm.batodama.parkshark.domain.membershiplevel;

public class Bronze extends MembershipLevel {


    public static final int MONTHLY_COST_BRONZE = 0;
    public static final double REDUCTION_PERCENTAGE_PER_HOUR_BRONZE = 0.0;
    public static final int MAX_ALLOWED_ALLOCATION_TIME_HOURS_BRONZE = 4;

    public Bronze() {
        super(MONTHLY_COST_BRONZE, REDUCTION_PERCENTAGE_PER_HOUR_BRONZE, MAX_ALLOWED_ALLOCATION_TIME_HOURS_BRONZE);
    }

}
