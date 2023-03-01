package com.br.pcsemdor.jsapi.business;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.br.pcsemdor.jsapi.model.Missao;
import com.br.pcsemdor.jsapi.repo.MissaoRepo;

@Service
public class MissaoBusiness {

	private MissaoRepo _repo;


	public MissaoBusiness(
			MissaoRepo _repo) {
		super();
		this._repo = _repo;
	}

	public List<Missao> getMissoes() {
		return _repo.findAll(Sort.by(Sort.Direction.ASC, "nomeMissao"));
	}
}
