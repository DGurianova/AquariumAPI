package com.gurianova.aquariumapi.exception;

public enum AquariumErrorCodes {
    //Search Fish error codes start with 1
    SEARCH_FISH_PAYLOAD_HAS_NO_PARAMETERS(1001,"Search payload has no parameters. Please provide at least one parameter in the payload ");

    //Other error code starts with 2 ... etc...

    AquariumErrorCodes(Integer code , String description) {
        this.code = code;
        this.description = description;

    }

    private Integer code;
    private String description;


    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
