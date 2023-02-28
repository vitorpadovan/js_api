package com.br.pcsemdor.jsapi.business;

import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.br.pcsemdor.jsapi.contracts.request.EventoRequest;
import com.br.pcsemdor.jsapi.model.Evento;
import com.br.pcsemdor.jsapi.repo.EventoRepo;
import com.br.pcsemdor.jsapi.util.ImageUtil;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class EventoBusiness extends BaseBusiness {

	private EventoRepo _repo;


	public EventoBusiness(
			EventoRepo _repo) {
		super();
		this._repo = _repo;
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
		var e = new Evento();
		e.setNomeEvento(evento.getNomeEvento());
		e.setSaveTheDate(evento.isSaveTheDate());
		e.setDataEvetno(evento.getDataEvento());
		e.setDataPublicacao(evento.getDataPublicacao());
		tratarImagem(arquivo, e);
		return e;
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
	public byte[] getImage(int i) throws NotFoundException {
		Optional<Evento> dbImage = _repo.findById(i);
		if (dbImage.isPresent()) {
			byte[] image = ImageUtil.decompressImage(dbImage.get().getImageData());
			return image;
		}
		throw new NotFoundException();
	}
}
