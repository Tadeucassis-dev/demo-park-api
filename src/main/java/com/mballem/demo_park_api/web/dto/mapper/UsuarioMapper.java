package com.mballem.demo_park_api.web.dto.mapper;

import com.mballem.demo_park_api.entity.Usuario;
import com.mballem.demo_park_api.web.dto.UsuarioCreateDto;
import com.mballem.demo_park_api.web.dto.UsuarioResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static Usuario toUsuario(UsuarioCreateDto createDto) {
        return new ModelMapper().map(createDto, Usuario.class);
    }

    public static UsuarioResponseDto toDto(Usuario usuario) {
        String role = usuario.getRole().name().substring("Role_".length());
        PropertyMap<Usuario, UsuarioResponseDto> props = new PropertyMap<Usuario, UsuarioResponseDto>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(props);
        return modelMapper.map(usuario, UsuarioResponseDto.class);
    }

    public static List<UsuarioResponseDto> dtoList(List<Usuario> usuarios) {
        return usuarios.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }
}