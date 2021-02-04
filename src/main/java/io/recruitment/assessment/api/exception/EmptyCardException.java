package io.recruitment.assessment.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED, reason = "empty.card.exception")
public class EmptyCardException extends RuntimeException {
}
