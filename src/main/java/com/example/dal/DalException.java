package com.example.dal;

public class DalException extends Exception {
   private static final long serialVersionUID = 1L;

   public DalException(String msg) {
      super(msg);
   }

   public DalException(Throwable cause) {
      super(cause);
   }

   public DalException(String msg, Throwable cause) {
      super(msg, cause);
   }
}
