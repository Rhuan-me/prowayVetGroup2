package API.REST.Zoologico.Zoologico.API.DTos;

public record AnimalDto(String nome, String especie, int idade, Long cuidador_id, Long habitat_id) {}
