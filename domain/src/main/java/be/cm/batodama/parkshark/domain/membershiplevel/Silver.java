package be.cm.batodama.parkshark.domain.membershiplevel;

public class Silver extends MembershipLevel {


    public static final int MONTHLY_COST_SILVER = 10;
    public static final double REDUCTION_PERCENTAGE_PER_HOUR_SILVER = 0.2;
    public static final int MAX_ALLOWED_ALLOCATION_TIME_HOURS_SILVER = 6;

    public Silver() {
        super(MONTHLY_COST_SILVER, REDUCTION_PERCENTAGE_PER_HOUR_SILVER, MAX_ALLOWED_ALLOCATION_TIME_HOURS_SILVER);
    }
}
