package be.cm.batodama.parkshark.domain.membershiplevel;

public abstract class MembershipLevel {

    private final int monthlyCost;
    private final double reductionPerHour;
    private final int maxAllowedAllocationTime;

    public MembershipLevel(int monthlyCost, double reductionPerHour, int maxAllowedAllocationTime) {
        this.monthlyCost = monthlyCost;
        this.reductionPerHour = reductionPerHour;
        this.maxAllowedAllocationTime = maxAllowedAllocationTime;
    }

    public int getMonthlyCost() {
        return monthlyCost;
    }

    public double getReductionPerHour() {
        return reductionPerHour;
    }

    public int getMaxAllowedAllocationTime() {
        return maxAllowedAllocationTime;
    }
}
