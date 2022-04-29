package com.example.amplify;

public class DatabaseContextHolder {

    private static final ThreadLocal<DatabaseEnviroment> CONTEXT = new ThreadLocal<>();

    public static void setContext(DatabaseEnviroment databaseEnviroment) {
        CONTEXT.set(databaseEnviroment);
    }

    public static DatabaseEnviroment getEnviroment() {
        return CONTEXT.get();
    }

    public static void reset() {
        CONTEXT.set(DatabaseEnviroment.UPDATABLE);

    }
}
