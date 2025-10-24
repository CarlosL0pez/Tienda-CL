package com.example.service;

import com.example.domain.Libro;
import com.example.repository.LibroRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    // LISTAR TODOS LOS LIBROS
    @Transactional(readOnly = true)
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    // OBTENER LIBRO POR ID
    @Transactional(readOnly = true)
    public Optional<Libro> obtenerLibro(Integer id) {
        return libroRepository.findById(id);
    }

    // GUARDAR LIBRO
    @Transactional
    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    // ELIMINAR LIBRO
    @Transactional
    public void eliminarLibro(Integer id) {
        if (!libroRepository.existsById(id)) {
            throw new IllegalArgumentException("El libro con ID " + id + " no existe.");
        }
        try {
            libroRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("No se puede eliminar el libro. Tiene datos asociados.", e);
        }
    }

    // ACTUALIZAR LIBRO
    @Transactional
    public void actualizarLibro(Integer id, String titulo, String autor, int anio) {
        Optional<Libro> optLibro = libroRepository.findById(id);
        if (optLibro.isPresent()) {
            Libro libro = optLibro.get();
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setAnio(anio);
            libroRepository.save(libro);
        } else {
            throw new IllegalArgumentException("El libro con ID " + id + " no existe.");
        }
    }
}
