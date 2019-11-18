package be.cm.batodama.parkshark.domain.parking;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PostCode {

    @Column(name = "POST_CODE")
    private String postCode;

    @Column(name = "POST_LABEL")
    private String postLabel;

    public PostCode() {
    }

    public PostCode(String postCode, String postLabel) {
        this.postCode = postCode;
        this.postLabel = postLabel;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getPostLabel() {
        return postLabel;
    }

    @Override
    public String toString() {
        return postCode + " " + postLabel;
    }
}
