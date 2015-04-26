package org.com.myapp.controller;

import java.util.NoSuchElementException;

import org.com.myapp.dao.DAOException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice extends
		ResponseEntityExceptionHandler {

	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleNoSuchElementException(NoSuchElementException e) {
		return e.getMessage();
	}

	@ExceptionHandler(ServiceException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public String handleNoMatchException(ServiceException e) {

		return e.getMessage();
	}

	@ExceptionHandler(DataAccessException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public String handleDataAccessException(DataAccessException e) {

		return e.getMessage();
	}

	@ExceptionHandler(DAOException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public String handleDataAccessException(DAOException e) {

		return e.getMessage();
	}

}
