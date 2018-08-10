package br.com.futmesa.api.exception;

import java.io.Serializable;

public class ErrorReturn
   implements Serializable
{

   private static final long serialVersionUID = 1L;

   private int statusCode;

   private String message;

   public ErrorReturn( int statusCode, String message )
   {
      this.statusCode = statusCode;
      this.message = message;
   }

   public ErrorReturn()
   {}

   public int getStatusCode()
   {
      return statusCode;
   }

   public void setStatusCode( int statusCode )
   {
      this.statusCode = statusCode;
   }

   public String getMessage()
   {
      return message;
   }

   public void setMessage( String message )
   {
      this.message = message;
   }
}
