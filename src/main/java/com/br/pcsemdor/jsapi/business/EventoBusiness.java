package com.br.pcsemdor.jsapi.business;

import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.br.pcsemdor.jsapi.contracts.request.EventoRequest;
import com.br.pcsemdor.jsapi.model.Evento;
import com.br.pcsemdor.jsapi.model.Missao;
import com.br.pcsemdor.jsapi.repo.EventoRepo;
import com.br.pcsemdor.jsapi.repo.MissaoRepo;
import com.br.pcsemdor.jsapi.util.ImageUtil;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class EventoBusiness extends BaseBusiness {

	private EventoRepo _repo;

	private MissaoRepo _repoMissao;


	public EventoBusiness(
			EventoRepo _repo,
			MissaoRepo _repoMissao) {
		super();
		this._repo = _repo;
		this._repoMissao = _repoMissao;
	}

	public Evento salvarEvento(EventoRequest request, MultipartFile arquivo) {
		return _repo.save(this.convertToEvento(request, arquivo));
	}

	public Evento salvarEvento(EventoRequest request) {
		return _repo.save(this.convertToEvento(request));
	}

	private Evento convertToEvento(EventoRequest evento) {
		return this.convertToEvento(evento, null);
	}

	private Evento convertToEvento(EventoRequest evento, MultipartFile arquivo) {
		// TODO adicionar tratamento de usuários logado
		// var user = this.getUser();
		tratarData(evento.getDataPublicacao(), "Data de publicacao", true);
		tratarData(evento.getDataEvento(), "Data de evento", false);
		var missao = requisitarMissao(evento.getIdMissao());
		var e = new Evento();
		e.setMissao(missao);
		e.setNomeEvento(evento.getNomeEvento());
		e.setSaveTheDate(evento.isSaveTheDate());
		e.setDataEvetno(evento.getDataEvento());
		e.setDataPublicacao(evento.getDataPublicacao());
		tratarImagem(arquivo, e);
		return e;
	}

	private Missao requisitarMissao(Integer idMissao) {
		var missao = this._repoMissao.findById(idMissao);
		if (!missao.isPresent())
			throw new RuntimeException("Missão de id " + idMissao + " não foi encontrada");
		return missao.get();
	}

	public List<Evento> getEventos() {
		return this._repo.findAll();
	}

	private void tratarData(Date dataParaValidar, String mensagem, boolean permiteNulo) {
		var hoje = Instant.now();
		if (dataParaValidar == null && permiteNulo) {
			throw new RuntimeException(mensagem + " nula ou não existe");
		}
		if (dataParaValidar.compareTo(Date.from(hoje)) < 0) {
			throw new RuntimeException(mensagem + " menor que a data atual");
		}
	}

	private void tratarImagem(MultipartFile arquivo, Evento e) {
		if (arquivo != null) {
			try {
				e.setImageData(ImageUtil.compressImage(arquivo.getBytes()));
			} catch (IOException | NullPointerException e1) {
				var teste = "Imagem não pode ser salva pelo erro: " + e1.getMessage();
				log.error(teste);
				throw new RuntimeException(teste);
			}
		}
	}

	@Transactional
	public byte[] getImage(int i) {
		Optional<Evento> dbImage = _repo.findById(i);
		if (dbImage.isPresent()) {
			byte[] image = ImageUtil.decompressImage(dbImage.get().getImageData());
			return image;
		}
		throw new RuntimeException("Id não tem imagem");
	}

	public String teste2(int i) {
		StringBuilder sb = new StringBuilder();
		sb.append("data:image/png;base64,");
		sb.append(new String(Base64.getEncoder().encode(getImage(i))));
		return sb.toString();
	}
}
