/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.example.dietatianjavafx.Models;

/**
 *
 * @author aleyn
 */
public enum DatabaseCredentialsFirebase {
    SEVICECONNECTION("{\n" +
    "  \"type\": \"service_account\",\n" +
    "  \"project_id\": \"diyet-dcb4e\",\n" +
    "  \"private_key_id\": \"a03172f95720136d2fd11af0077f57c8a3184403\",\n" +
    "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCU05fkafiqK/7N\\n01nw9Gda1E8TOnvNPWeS5O5yDH9RHtgnv1vkjFlM3EqaruTo/ZR0VK7g19iLdS37\\n46+Piy2Ub+plRxySIatxkG0d8opfDAty3eIUWlRHotI5McUJNAv+CLywXVSGZ4zR\\nowW28a/GpYPE7LjyNaMPQT4EsaeLNS4nClXKAGJmXgRTF2KMKnE7PlCKZVI2Ghrm\\naT4SPPJ7aLsrS/wNdrncF56MPNq30zfwMtmQ4g+97rcEmBeITmqLf6acvBLa9kZm\\n9ivBkAetb6YQmr1SHDp5vzLfvZEuH67DfOAPkg0rhI83nYIuHj1zxlMXz1G96Y5R\\n+piJf5bJAgMBAAECggEAFgXhujk8ROJRyF94NcHU8YntUakb3NkzVHdwGYxj4rsv\\nvWIB1SY3qLoIiC9NTHUJ3NUTM859CtYCQf4FYrZzvj15cBc/0RgOaLW4j+/uLfZ4\\nUz0mUWC0jA06EqCcyancBHGCio6lDWaRXgxDsji9jCW+BAwLqjvprz42UTeA1YaF\\nQNm8JlybYUcBQhbJAV3tCUkRZgtuOeoewhmi355iMHqreurYwzDUpHIbDqQWkT9C\\n3F+QQ6XCBzVSH3hWKvhVCQw7KuFfgdY7zJSx6snmELNKrRxMRoNP6MJod+FDZ0x8\\nAwYqXe5en4OCN0qRue5Z/z2PWy7vTJkzgpQIE1w+IQKBgQDNILSrHwavE6eI1Zcf\\nuel9XfLDCs2OZDurQq1aqZAz05ujdsbpeFDHPbREbsZ5teDfDVVw/eWeXKhhm9cv\\nzDNHx0Lo4ICsEiNz3zE5D4kdghXgKvVZKilM72G5M08YCUcr26/PCDAGHNU081iP\\nKsMLe84hiIb8+ClnVz/fGqh9rQKBgQC5vGV6hE9W/R957inBQoXWsKDZtEJ+rSrq\\n2xrW+BV+b0nYyP7TLPFecabImBLUYYJQjehR8joCfHslSumehvtM9zQGPtTCTEnU\\nnuFWmJXA+VGNtpSea25Zm9TqpV06dk95re8Hhfbko3TCChG2O8qX32EHCmQxn6wx\\ncEMFryKpDQKBgQCOZOPF3lXPdz9Xd53ewjnZczwwNPGYpO/e5Pxd8VNPBtgF01Al\\nhgXdKXJYBuX0W1Nsw17KfxXEpuGkUIBCh+gZLbO+4QmxaS3/I7KRv70ZJNJ2uJt0\\neBYPeCVIYNvI/bfQNEKI31AnDiDbDwos6CxZbmobUSETvllPiXunLmRESQKBgHFk\\nDOfxIqbOvYqo1aEAxci96FVAcV/4xDzBKu+WYTjhJEpO2xN9WpX3+IR6IvS52t7m\\n47AzmyqtaFjqFRoM+hqQyJgPf8Bm5qQBozxExxganalQkAbvO11Ff4UJE5N/ayG0\\nCDulk4ceK0fS8eE5O4PggdHUjDqnam58RM6Rjy15AoGAEYQ/lBFMHN2Aeqh/Qo8z\\nLLdsR+CbLDaQslyKP2L5tJun59Du84csH2lkU5pjH4exrUa8wgwoKzeAbV2qy6Ws\\nooW99j28pZcDlEPNiM/p8pzyJhAhFQlu2WBnvrLddaUsGAuyiws4rlnmopGo6zUQ\\ndneg3PaEyiA0R4a63E6Pwfs=\\n-----END PRIVATE KEY-----\\n\",\n" +
    "  \"client_email\": \"firebase-adminsdk-dv4al@diyet-dcb4e.iam.gserviceaccount.com\",\n" +
    "  \"client_id\": \"116453230918760609193\",\n" +
    "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
    "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
    "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
    "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-dv4al%40diyet-dcb4e.iam.gserviceaccount.com\",\n" +
    "  \"universe_domain\": \"googleapis.com\"\n" +
    "}\n" +
    ""),
    URLDATABASE("https://diyet-dcb4e.firebaseio.com/");
    
    private final String VALUE;

    private DatabaseCredentialsFirebase(String VALUE) {
        this.VALUE = VALUE;
    }

    public String getVALUE() {
        return VALUE;
    }  
}
