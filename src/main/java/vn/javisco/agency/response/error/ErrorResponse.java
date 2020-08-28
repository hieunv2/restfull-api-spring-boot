package vn.javisco.agency.response.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.javisco.agency.response.base.BaseResponse;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse implements BaseResponse {
    private String reason;
    private boolean error = true;

    public ErrorResponse(String reason) {
        this.reason = reason;
    }
}
