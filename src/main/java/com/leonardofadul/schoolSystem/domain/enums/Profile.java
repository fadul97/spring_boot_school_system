package com.leonardofadul.schoolSystem.domain.enums;

public enum Profile {

    PROFESSOR(1, "ROLE_PROFESSOR"),
    STUDENT(2, "ROLE_STUDENT");

    private int code;
    private String description;

    private Profile(int code, String description){
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Profile toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (Profile x : Profile.values()) {
            if (cod.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid Id: " + cod);
    }

}
