package com.leadsponge.IO.security;

public interface SecurityService {
    String findLoggedInLogin();

    void autoLogin(String login, String senha);
}