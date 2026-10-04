package com.automation.core.context;

public final class ScenarioContextManager {

    private static final ThreadLocal<ScenarioContext> CONTEXT =
            ThreadLocal.withInitial(ScenarioContext::new);

    private ScenarioContextManager() {
    }

    public static ScenarioContext getContext() {
        return CONTEXT.get();
    }

    public static void removeContext() {
        CONTEXT.remove();
    }
}