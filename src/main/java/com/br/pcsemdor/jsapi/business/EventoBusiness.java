package com.br.pcsemdor.jsapi.business;

import java.io.IOException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.br.pcsemdor.jsapi.contracts.request.EventoRequest;
import com.br.pcsemdor.jsapi.model.Evento;
import com.br.pcsemdor.jsapi.repo.EventoRepo;
import com.br.pcsemdor.jsapi.util.ImageUtil;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class EventoBusiness {

	private EventoRepo _repo;


	public EventoBusiness(
			EventoRepo _repo) {
		super();
		this._repo = _repo;
	}

	public Evento salvarEvento(EventoRequest request) {
		log.info(request.toString());
		return _repo.save(this.convertToEvento(request));
	}

	public void salvarImagem() {
	}

	private Evento convertToEvento(EventoRequest evento) {
		var e = new Evento();
		e.setNomeEvento(evento.getNomeEvento());
		e.setSaveTheDate(evento.isSaveTheDate());
		e.setDataEvetno(evento.getDataEvento());
		e.setDataPublicacao(evento.getDataPublicacao());
		try {
			e.setImageData(ImageUtil.compressImage(evento.getFile().getBytes()));
		} catch (IOException e1) {
			log.error("Imagem não pode ser salva");
		}
		return e;
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
