package com.example.bookmyshow.Exceptions;

public class MovieAlreadyExistsException extends Exception{
  public MovieAlreadyExistsException(String message) {
    super(message);
  }
}
