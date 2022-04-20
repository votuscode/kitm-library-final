package com.kitm.library.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.kitm.library.api", "com.kitm.library.backend"})
public class LibraryBackend {

  public static void main(String[] args) {
    SpringApplication.run(LibraryBackend.class, args);
  }
}
