package be.cm.batodama.parkshark.api.allocation.dtos;

public class AllocationMemberDto {
    private long id;
    private String username;

    public AllocationMemberDto(long id, String username) {
        this.id = id;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}


