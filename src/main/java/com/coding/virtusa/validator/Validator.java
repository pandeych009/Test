package com.coding.virtusa.validator;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class Validator<T> {

	private List<Predicate<T>> validators = new LinkedList<>();

	
	public Validator() {
		super();
	}

	public Validator(List<Predicate<T>> validators) {
		this.validators = validators;
	}
	
	public void addValidator(Predicate<T> validator) {
		validators.add(validator);
	}
	
	public void addValidators(List<Predicate<T>> validators) {
		validators.addAll(validators);
	}
	
	
	public boolean test(final T validator) {
		return validators.stream().allMatch(input -> input.test(validator));
	}
	
	
	
	
	
}
