package com.sherlockcodes.libraryDemo.web;

/**
 * Created by ayush on 7/27/16.
 */
public class ExceptionMessage {
    public ExceptionMessage(String message) {
        new response(message);
    }

    class response{
        public response(String message) {this.message=message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        String message;
        String status="ERROR";
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
