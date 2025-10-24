package com.example.repository;

import com.example.domain.Sugerencia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SugerenciaRepository extends JpaRepository<Sugerencia, Long> {
    List<Sugerencia> findByTipo(Sugerencia.TipoSugerencia tipo);
}
