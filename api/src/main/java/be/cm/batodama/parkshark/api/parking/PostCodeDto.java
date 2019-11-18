package be.cm.batodama.parkshark.api.parking;

public class PostCodeDto {

    public String postCode;

    public String postLabel;

    public PostCodeDto(String postCode, String postLabel) {
        this.postCode = postCode;
        this.postLabel = postLabel;
    }
}
