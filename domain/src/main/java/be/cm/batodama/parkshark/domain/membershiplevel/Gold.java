package be.cm.batodama.parkshark.domain.membershiplevel;

public class Gold extends MembershipLevel {


    public static final int MONTHLY_COST_GOLD = 40;
    public static final double REDUCTION_PERCENTAGE_PER_HOUR_GOLD = 0.3;
    public static final int MAX_ALLOWED_ALLOCATION_TIME_HOURS_GOLD = 24;

    public Gold() {
        super(MONTHLY_COST_GOLD, REDUCTION_PERCENTAGE_PER_HOUR_GOLD, MAX_ALLOWED_ALLOCATION_TIME_HOURS_GOLD);
    }
}
