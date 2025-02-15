package br.com.ifba.pweb.mapper;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ifba.pweb.dto.SalaDto;
import br.com.ifba.pweb.entity.Sala;

public class SalaMapper {
    public static Sala toEntity(SalaDto dto) {
        Sala sala = new Sala();
        sala.setCodigo(dto.codigo());
        sala.setNome(dto.nome());
        return sala;
    }
    
    public static SalaDto toDTO(Sala sala) {
        return new SalaDto(sala.getId(), sala.getCodigo(), sala.getNome());
    }
    
    public static List<SalaDto> toDTOList(List<Sala> salas) {
        return salas.stream().map(SalaMapper::toDTO).collect(Collectors.toList());
    }
}