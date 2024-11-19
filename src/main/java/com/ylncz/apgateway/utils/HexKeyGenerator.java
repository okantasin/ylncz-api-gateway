package com.ylncz.apgateway.utils;

import java.security.SecureRandom;

public class HexKeyGenerator {

    public static void main(String[] args) {
        // Generate a 256-bit key
        String hexKey = generateHexKey(32); // 256 bit = 32 byte
        System.out.println("Generated Hex Key: " + hexKey);
    }

    private static String generateHexKey(int sizeInBytes) {
        // Use SecureRandom for cryptographic security
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[sizeInBytes];
        secureRandom.nextBytes(key);

        // Convert byte array to hexadecimal string
        StringBuilder hexString = new StringBuilder();
        for (byte b : key) {
            hexString.append(String.format("%02x", b)); // Format each byte to 2 hexadecimal digits
        }
        return hexString.toString();
    }
}