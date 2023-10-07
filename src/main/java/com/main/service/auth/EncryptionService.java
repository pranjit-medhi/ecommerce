package com.main.service.auth;

public interface EncryptionService {
    public String encryptPassword(String password);
    public boolean verifyPassword(String password, String hash);
}
