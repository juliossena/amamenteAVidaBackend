package com.julio.amamenteAVida.external.enums;

public enum Profile {

    ADMIN(0, "ROLE_ADMIN"),
    CLIENT(1, "ROLE_CLIENTE");

    private int code;
    private String description;


    private Profile(final int code, final String description) {
        this.code = code;
        this.description = description;
    }

    public int getCod() {
        return this.code;
    }

    public String getDescricao() {
        return this.description;
    }

    public static Profile toEnum(final Integer code) {
        if (code == null) {
            return null;
        }

        for (final Profile x : Profile.values()) {
            if (code.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + code);
    }
}
