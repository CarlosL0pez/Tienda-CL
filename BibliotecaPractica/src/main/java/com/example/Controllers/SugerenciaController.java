package com.example.service;

import com.example.domain.Sugerencia;
import com.example.repository.SugerenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SugerenciaController {

    @Autowired
    private SugerenciaRepository SugerenciaRepository;

    @Transactional
    public void guardarSugerencia(Sugerencia sugerencia) {
        SugerenciaRepository.save(sugerencia);
    }

    @Transactional(readOnly = true)
    public List<Sugerencia> listarSugerencia() {
        return SugerenciaRepository.findAll();
    }

    @Transactional
    public void marcarTratado(Long id) {
        Sugerencia sugerencia = SugerenciaRepository.findById(id).orElse(null);
        if (Sugerencia != null) {
            Sugerencia.setTratado(true);
            SugerenciaRepository.save(sugerencia);
        }
    }
}

