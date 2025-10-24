package com.example.Controller;

import com.example.domain.Libro;
import com.example.service.LibroService;
import jakarta.validation.Valid;
import java.util.Locale;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private MessageSource messageSource;

    // LISTADO DE LIBROS
    @GetMapping("/listado")
    public String listado(Model model) {
        var libros = libroService.listarLibros();
        model.addAttribute("libros", libros);
        model.addAttribute("totalLibros", libros.size());
        return "/libro/listado";
    }

    // GUARDAR LIBRO (CREAR O MODIFICAR)
    @PostMapping("/guardar")
    public String guardar(@Valid Libro libro, @RequestParam(required = false) MultipartFile imagenFile,
                          RedirectAttributes redirectAttributes) {

        libroService.guardarLibro(libro, imagenFile);
        redirectAttributes.addFlashAttribute("todoOk",
                messageSource.getMessage("mensaje.actualizado", null, Locale.getDefault()));

        return "redirect:/libro/listado";
    }

    // ELIMINAR LIBRO
    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
        String titulo = "todoOk";
        String detalle = "mensaje.eliminado";
        try {
            libroService.eliminarLibro(id);
        } catch (IllegalArgumentException e) {
            titulo = "error";
            detalle = "libro.error01"; // libro no existe
        } catch (IllegalStateException e) {
            titulo = "error";
            detalle = "libro.error02"; // libro tiene datos asociados
        } catch (Exception e) {
            titulo = "error";
            detalle = "libro.error03"; // error inesperado
        }
        redirectAttributes.addFlashAttribute(titulo,
                messageSource.getMessage(detalle, null, Locale.getDefault()));
        return "redirect:/libro/listado";
    }

    // MODIFICAR LIBRO
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Libro> libroOpt = libroService.obtenerLibro(id);
        if (libroOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error",
                    messageSource.getMessage("libro.error01", null, Locale.getDefault()));
            return "redirect:/libro/listado";
        }
        model.addAttribute("libro", libroOpt.get());
        return "/libro/modifica";
    }
}
