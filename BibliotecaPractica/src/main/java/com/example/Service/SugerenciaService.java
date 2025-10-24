package com.example.service;

import com.example.domain.Sugerencia;
import com.example.repository.SugerenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SugerenciaService {

    private final SugerenciaRepository sugerenciaRepository;

    @Autowired
    public SugerenciaService(SugerenciaRepository sugerenciaRepository) {
        this.sugerenciaRepository = sugerenciaRepository;
    }

    // Listar todas las sugerencias
    public List<Sugerencia> listarSugerencias() {
        return sugerenciaRepository.findAll();
    }

    // Guardar una nueva sugerencia
    public void guardar(Sugerencia sugerencia) {
        sugerenciaRepository.save(sugerencia);
    }

    // Obtener una sugerencia por ID
    public Sugerencia obtenerSugerencia(Long id) {
        return sugerenciaRepository.findById(id).orElse(null);
    }

    // Eliminar una sugerencia por ID
    public void eliminarSugerencia(Long id) {
        sugerenciaRepository.deleteById(id);
    }
    
}
