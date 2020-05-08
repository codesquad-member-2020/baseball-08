package dev.codesquad.java.baseball08.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class RequestBody {

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("client_secret")
    private String clientSecret;

    @JsonProperty("code")
    private String code;

    public RequestBody() {}

    public static class Builder {
        private String clientId;
        private String clientSecret;
        private String code;

        public Builder() {}

        public Builder clientId(String val) {
            clientId = val;
            return this;
        }

        public Builder clientSecret(String val) {
            clientSecret = val;
            return this;
        }

        public Builder code(String val) {
            code = val;
            return this;
        }

        public RequestBody build() {
            Assert.hasText(clientId,"ClientId must not be null");
            Assert.hasText(clientSecret,"ClientSecret must not be null");
            Assert.hasText(code,"Code must not be null");
            return new RequestBody(this);
        }
    }

    private RequestBody(Builder builder) {
        clientId = builder.clientId;
        clientSecret = builder.clientSecret;
        code = builder.code;
    }

    @Override
    public String toString() {
        return "RequestBody{" +
                "clientId='" + clientId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
