package com.saborgourmet.restaurante.aop;

import com.saborgourmet.restaurante.domain.entities.Auditoria;
import com.saborgourmet.restaurante.domain.persistence.AuditoriaRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class AuditoriaAspect {

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    // 🔹 Captura los métodos que empiecen con "guardar", "eliminar" o "editar"
    @Pointcut("execution(* com.saborgourmet.restaurante.controllers.*.guardar*(..)) || " +
            "execution(* com.saborgourmet.restaurante.controllers.*.eliminar*(..)) || " +
            "execution(* com.saborgourmet.restaurante.controllers.*.editar*(..))")
    public void accionesAuditoria() {}

    @AfterReturning("accionesAuditoria()")
    public void registrarAuditoria(JoinPoint joinPoint) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String usuario = (auth != null) ? auth.getName() : "anónimo";

        String metodo = joinPoint.getSignature().getName();
        String clase = joinPoint.getTarget().getClass().getSimpleName();
        String tabla = clase.replace("Controller", ""); // Ejemplo: MesaController -> Mesa
        String tipo = metodo.startsWith("guardar") ? "INSERT/UPDATE"
                : metodo.startsWith("eliminar") ? "DELETE"
                : "UPDATE";

        Long idRegistro = null;

        // Buscar si hay un argumento con ID
        for (Object arg : joinPoint.getArgs()) {
            try {
                var idField = arg.getClass().getDeclaredField("id" + tabla);
                idField.setAccessible(true);
                idRegistro = (Long) idField.get(arg);
            } catch (Exception ignored) {}
        }

        Auditoria auditoria = new Auditoria(
                LocalDateTime.now(),
                tabla,
                tipo,
                idRegistro,
                usuario
        );

        auditoriaRepository.save(auditoria);

        System.out.println("📝 Auditoría registrada: " + auditoria.getTabla() +
                " - " + auditoria.getTipo() + " - Usuario: " + usuario);
    }
}
