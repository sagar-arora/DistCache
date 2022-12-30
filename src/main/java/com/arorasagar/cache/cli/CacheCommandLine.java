package com.arorasagar.cache.cli;

import com.arorasagar.cache.client.CacheClient;

import java.util.Scanner;

public class CacheCommandLine {
    public CacheCommandLine() {

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the port you want to connect");
        int port = scanner.nextInt();
        CacheClient cacheClient = CacheClient.builder()
                .address("localhost")
                .port(port)
                .build();

        while (true) {
            System.out.println("Choose between - 1, 2, 3 and 4");
            int val = scanner.nextInt();
            scanner.nextLine();
            switch (val) {
                case 1:
                    System.out.println("Enter the key:");
                    String key = scanner.nextLine();
                    System.out.println("You entered: " + key);
                    String result = cacheClient.get(key.trim());
                    System.out.println("Result is: " + result);
                    break;

                case 2:
                    System.out.println("Enter the key:");
                    String key1 = scanner.nextLine();
                    System.out.println("Entered the key:" + key1);
                    System.out.println("Enter the value:");
                    String value = scanner.nextLine().trim();
                    System.out.println("Entered the value:" + value);

                    String result1 = cacheClient.put(key1.trim(), value.trim());
                    System.out.println("Result is: " + result1);
                    break;

                case 3:
                    System.out.println("Enter the port you want to connect");
                    port = scanner.nextInt();
                    cacheClient = CacheClient.builder()
                            .address("localhost")
                            .port(port)
                            .build();
                    break;
                case 4:
                    System.out.println("exiting");
                    System.exit(0);
                    break;
                default:
                    System.out.println("doesn't match");
            }
        }
    }
}
