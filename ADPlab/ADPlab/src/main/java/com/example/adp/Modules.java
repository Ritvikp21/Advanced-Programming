package com.example.adp;

public class Modules {
    private String ModuleName;
    private long code;
    public Modules(final String ModuleName,
                  final long code) {
        this.ModuleName = ModuleName;
        this.code = code;
    }
    public String getModuleName() {
        return this.ModuleName;
    }
    public long getCode() {
        return this.code;
    }

}
