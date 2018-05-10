package com.user.validation;

public interface Validator<I> {
	boolean validate(I data);
}
