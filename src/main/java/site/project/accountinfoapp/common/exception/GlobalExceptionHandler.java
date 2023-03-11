package site.project.accountinfoapp.common.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Locale;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ConstraintViolationResolver violationMessageResolver;
    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception e) {
        log.error("INTERNAL SERVER ERROR: ", e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {
        log.error("ConstraintViolationException: ", e);

        List<FieldErrorDetail> fieldErrorDetails = e.getConstraintViolations().stream()
                .map(violationMessageResolver::toFiledErrorDetail).toList();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorCode.INVALID_PARAMETER + fieldErrorDetails.toString());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException -> errorCode: {}, errorMessage: {}", e.getErrorCode(), getErrorMessage(e));

        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(errorCode + getErrorMessage(e));
    }

    /**
     * {@link BusinessException#getCode()} 메서드로 code를 가져와 MessageSource 통해 매칭되는 오류 메시지를 가져온다.
     *
     * @param businessException
     * @return
     */
    private String getErrorMessage(BusinessException businessException) {
        String errorMessage;

        try {
            errorMessage = messageSource.getMessage(businessException.getCode(),
                    businessException.getArguments() != null ? businessException.getArguments().toArray() : null,
                    Locale.getDefault());
        } catch (NoSuchMessageException e) {
            errorMessage = messageSource.getMessage(businessException.getErrorCode().getCode(), null, Locale.getDefault());
        }

        return errorMessage;
    }

}
