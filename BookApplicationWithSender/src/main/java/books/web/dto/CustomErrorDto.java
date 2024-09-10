package books.web.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public record CustomErrorDto (
        boolean isError,
        int errorCode,
        String errorMessage
) {

   public static ResponseEntity<CustomErrorDto> notFound(String resourceName) {
       HttpStatus httpStatus = HttpStatus.NOT_FOUND;
       return new ResponseEntity<>(
               new CustomErrorDto(true, httpStatus.value(), resourceName +  " Not Found"),
               httpStatus
       );
   }

}
