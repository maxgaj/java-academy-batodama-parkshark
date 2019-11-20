package be.cm.batodama.parkshark.domain.membershiplevel;

public enum MembershipLevel {


    BRONZE(0,
            0.0,
            4),
    SILVER(10,
            0.2,
            6),
    GOLD(40,
            0.3,
            24);

    private final int monthlyCost;
    private final double reductionPercentagePerHour;
    private final int maxAllowedAllocationTime;

    MembershipLevel(int monthlyCost, double reductionPercentagePerHour, int maxAllowedAllocationTime) {
        this.monthlyCost = monthlyCost;
        this.reductionPercentagePerHour = reductionPercentagePerHour;
        this.maxAllowedAllocationTime = maxAllowedAllocationTime;
    }

    public int getMonthlyCost() {
        return monthlyCost;
    }

    public double getReductionPercentagePerHour() {
        return reductionPercentagePerHour;
    }

    public int getMaxAllowedAllocationTime() {
        return maxAllowedAllocationTime;
    }
}
