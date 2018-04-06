package br.com.academia.exceptions;

public class ArgumentTypeMismatchException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ArgumentTypeMismatchException(String msg)
	{
		super(msg);
	}
	
	public ArgumentTypeMismatchException(String msg, Throwable cause)
	{
		super(msg, cause);
	}
}