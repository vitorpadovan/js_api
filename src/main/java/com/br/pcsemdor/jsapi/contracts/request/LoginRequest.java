package com.br.pcsemdor.jsapi.contracts.request;

import lombok.Data;

@Data
public class LoginRequest {

	private String login;

	private String senha;
}
