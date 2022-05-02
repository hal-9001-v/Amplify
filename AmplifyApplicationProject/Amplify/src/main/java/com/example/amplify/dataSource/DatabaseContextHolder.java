package com.example.amplify.dataSource;

import com.example.amplify.DatabaseEnviroment;

public class DatabaseContextHolder {

    private static final ThreadLocal<DatabaseEnviroment> CONTEXT = new ThreadLocal<>();

    public static void setContext(DatabaseEnviroment databaseEnviroment) {
        CONTEXT.set(databaseEnviroment);
    }

    public static DatabaseEnviroment getEnvironment() {
        return CONTEXT.get();
    }

    public static void reset() {
        CONTEXT.set(DatabaseEnviroment.UPDATABLE);

    }
}
