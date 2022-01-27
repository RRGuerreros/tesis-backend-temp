package com.sidet.idat.ws.medisalud.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.sidet.idat.ws.medisalud.entity.Modulo;
import com.sidet.idat.ws.medisalud.entity.Usuario;
import com.sidet.idat.ws.medisalud.repository.UsuarioRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("realizando la verificación de authenticación del usuario => {}", username);

		Usuario usuario = usuarioRepository.buscar(username);
		
		if( usuario == null ) {
			log.info("no se encontró el usuario => {}", username );
			throw new UsernameNotFoundException("Usuario no existe");
		}
		
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		List<Modulo> modulos = usuarioRepository.buscarRoles( usuario.getUsuarioId() );
		
		if( modulos.isEmpty() ) {
			log.info("no se encontró roles para el usuario => {}", username );
			throw new UsernameNotFoundException("No se encontró el usuario y sus roles");
		}
		
		for ( Modulo modulo : modulos) {
			String[] rolesAgrupados = modulo.getRolesAgrupados().split(",");
			for ( String rolString : rolesAgrupados) {
				roles.add( new SimpleGrantedAuthority(rolString));
			}
		}

		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, roles);
	}
	
	public Usuario buscarUsuario( String username ) {
		
		Usuario usuario = usuarioRepository.buscar(username);
		
		if( usuario == null ) {
			log.info("no se encontró el usuario => {}", username );
			throw new UsernameNotFoundException("Usuario no existe");
		}
		
		List<Modulo> modulos = usuarioRepository.buscarRoles( usuario.getUsuarioId() );
		
		if( modulos.isEmpty() ) {
			log.info("no se encontró roles para el usuario => {}", username );
			throw new UsernameNotFoundException("No se encontró el usuario y sus roles");
		}
		
		usuario.setModulos(modulos);
		return usuario;
	}
}
