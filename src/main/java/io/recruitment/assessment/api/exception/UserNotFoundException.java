package io.recruitment.assessment.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "user.not.found.exception")
public class UserNotFoundException extends RuntimeException {
}
