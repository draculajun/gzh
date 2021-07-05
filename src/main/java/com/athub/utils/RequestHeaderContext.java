package com.athub.utils;

public class RequestHeaderContext {

    private static final ThreadLocal<RequestHeaderContext> REQUEST_HEADER_CONTEXT_THREAD_LOCAL = new ThreadLocal<>();

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    private String accessToken;

    public static RequestHeaderContext getInstance() {
        return REQUEST_HEADER_CONTEXT_THREAD_LOCAL.get();
    }

    public static void setContext(RequestHeaderContext context) {
        REQUEST_HEADER_CONTEXT_THREAD_LOCAL.set(context);
    }

    public static void clean() {
        REQUEST_HEADER_CONTEXT_THREAD_LOCAL.remove();
    }

    private RequestHeaderContext(RequestHeaderContextBuild requestHeaderContextBuild) {
        this.accessToken = requestHeaderContextBuild.accessToken;
        setContext(this);
    }

    public static class RequestHeaderContextBuild {
        private String accessToken;

        public RequestHeaderContextBuild accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public RequestHeaderContext bulid() {
            return new RequestHeaderContext(this);
        }
    }

}