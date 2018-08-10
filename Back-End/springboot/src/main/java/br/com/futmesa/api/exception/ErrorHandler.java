package br.com.futmesa.api.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler
{

   @ResponseStatus ( HttpStatus.BAD_REQUEST )
   @ExceptionHandler ( MissingServletRequestParameterException.class )
   public @ResponseBody ErrorReturn handleBadRequestMissingServletRequestParameterException( HttpServletRequest req, Exception ex )
   {
      return new ErrorReturn( HttpStatus.BAD_REQUEST.value(), ex.getMessage() );
   }

   @ResponseStatus ( HttpStatus.BAD_REQUEST )
   @ExceptionHandler ( org.springframework.http.converter.HttpMessageNotReadableException.class )
   public @ResponseBody ErrorReturn handleIlegalArgument2( HttpServletRequest req, Exception ex )
   {
      return new ErrorReturn( HttpStatus.BAD_REQUEST.value(), ex.getMessage() );
   }

   @ResponseStatus ( HttpStatus.INTERNAL_SERVER_ERROR )
   @ExceptionHandler ( Exception.class )
   public @ResponseBody ErrorReturn handleInternalError( HttpServletRequest req, Exception ex )
   {
      return new ErrorReturn( HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage() );
   }
}
