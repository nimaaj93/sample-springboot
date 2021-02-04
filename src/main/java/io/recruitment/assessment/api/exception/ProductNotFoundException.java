package io.recruitment.assessment.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "product.not.found.exception")
public class ProductNotFoundException extends RuntimeException {
}
