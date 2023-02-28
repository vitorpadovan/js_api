package com.br.pcsemdor.jsapi.exception;

import java.util.Map;

import com.br.pcsemdor.jsapi.exception.contract.MessageException;

public abstract class BaseRuntimeException extends RuntimeException implements MessageException {

	private final Map<String, Object> mapDetails;


	public BaseRuntimeException() {
		mapDetails = null;
	}

	public abstract String getExceptionKey();

	@Override
	public Map<String, Object> getMapDetails() {
		return this.mapDetails;
	}
}
