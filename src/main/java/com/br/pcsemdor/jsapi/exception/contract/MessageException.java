package com.br.pcsemdor.jsapi.exception.contract;

import java.util.Map;

public interface MessageException {

	String getExceptionKey();

	Map<String, Object> getMapDetails();
}
