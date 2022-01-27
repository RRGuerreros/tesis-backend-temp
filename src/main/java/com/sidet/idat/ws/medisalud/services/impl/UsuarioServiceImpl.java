package com.sidet.idat.ws.medisalud.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sidet.idat.ws.medisalud.entity.Usuario;
import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;
import com.sidet.idat.ws.medisalud.entity.dto.UsuarioDTO;
import com.sidet.idat.ws.medisalud.exceptions.DatabaseException;
import com.sidet.idat.ws.medisalud.exceptions.MedisaludSidetException;
import com.sidet.idat.ws.medisalud.mappers.UsuarioMapper;
import com.sidet.idat.ws.medisalud.repository.UsuarioRepository;
import com.sidet.idat.ws.medisalud.services.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService<UsuarioDTO> {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	@Override
	public List<UsuarioDTO> listarTodos() {
		return null;
	}

	@Override
	public UsuarioDTO buscarPorId(Integer usuarioId) {
		log.info("UsuarioServiceImpl.buscarPorId( usuarioId = {})", usuarioId);
		try {
			return usuarioMapper.asUsuarioDTO(usuarioRepository.buscarPorId(usuarioId));
		} catch (Exception e) {
			throw new MedisaludSidetException("Error al buscar por id el usuario.", e);
		}
	}

	@Override
	public UsuarioDTO registrar(UsuarioDTO usuarioDTO) {
		log.info("UsuarioServiceImpl.registrar( usuario = {})", usuarioDTO);
		
		Integer resp =usuarioRepository.validarSiTieneUsuario(usuarioDTO.getPersonaId());
		
		if(resp != null) {
			log.info("la persona cuenta con un usuario en la base de datos");
			throw new MedisaludSidetException("El personal seleccionado ya cuenta con un usuario en el sistema, no puede crear esta cuenta.");
		}
		
		try {
			Usuario newUsuario = usuarioMapper.asUsuario(usuarioDTO);
			/*
			BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
			
			String passEncode = encode.encode(newUsuario.getPassword());
			
			newUsuario.setPassword(passEncode);
			*/
			usuarioRepository.registrar(newUsuario);
			usuarioDTO.setUsuarioId( newUsuario.getUsuarioId() );
		} catch (Exception e) {
			throw new MedisaludSidetException("Error al registrar el usuario.", e);
		}
		return usuarioDTO;
	}

	@Override
	public UsuarioDTO actualizar(UsuarioDTO usuarioDTO) {
		log.info("UsuarioServiceImpl.registrar( actualizar = {})", usuarioDTO);
		try {
			Usuario newUsuario = usuarioMapper.asUsuario(usuarioDTO);
			usuarioRepository.actualizar(newUsuario);
		} catch (Exception e) {
			throw new MedisaludSidetException("Error al actualizar el usuario.", e);
		}
		return usuarioDTO;
	}

	@Override
	public Boolean eliminar(Integer usuarioId) {
		log.info("UsuarioServiceImpl.eliminar( usuarioId = {})", usuarioId);
		try {
			usuarioRepository.eliminar(usuarioId);
		} catch (Exception e) {
			throw new MedisaludSidetException("Error al eliminar el usuario.", e);
		}
		return true;
	}

	@Override
	public PaginadorDTO<UsuarioDTO> listarPaginado(PaginadorDTO<UsuarioDTO> paginador) {
		log.info("UsuarioServiceImpl.listarPaginado( paginador = {})", paginador);
		
		Integer inicio = 0;

		if( paginador.getNumeroPagina() == 0 || paginador.getNumeroPagina() == 1 ) {
			inicio = 0;
		} else {
			inicio = (paginador.getNumeroPagina() - 1) * paginador.getTotalFilasPagina();
		}
		
		try {
			
			List<Usuario> datos = usuarioRepository.listarPaginado( inicio, paginador.getTotalFilasPagina() );
			Integer totalFilas = usuarioRepository.contarTotalFilas();
			
			paginador.setTotalFilas(totalFilas);
			paginador.setDatos( usuarioMapper.asUsuarioDTOs(datos) );
			
		} catch (Exception e) {
			throw new DatabaseException( "Error, no se pudo listar los datos de usuarios.", e );
		}
		return paginador;
	}

	@Override
	public Boolean bloquear(Integer usuarioId) {
		log.info("UsuarioServiceImpl.bloquear( usuarioId = {})", usuarioId);
		try {
			usuarioRepository.bloquear(usuarioId);
			return true;
		} catch (Exception e) {
			throw new MedisaludSidetException("Error al bloquear al usuario", e);
		}
	}

	@Override
	public Boolean desbloquear(Integer usuarioId) {
		log.info("UsuarioServiceImpl.desbloquear( usuarioId = {})", usuarioId);
		try {
			usuarioRepository.desbloquear(usuarioId);
			return true;
		} catch (Exception e) {
			throw new MedisaludSidetException("Error al desbloquear al usuario", e);
		}
	}
}
