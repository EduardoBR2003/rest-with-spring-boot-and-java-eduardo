package br.com.eduardo.exception;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {


}
