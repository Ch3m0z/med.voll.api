package med.voll.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendarConsultaDTO;
import med.voll.api.domain.consulta.AgendarConsultaService;
import med.voll.api.domain.consulta.DatosDetalleConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendarConsultaService service;

    @Operation(summary = "Get a product by id", description = "Returns a product as per the id")
    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid AgendarConsultaDTO datos){
        var dto = service.agendar(datos);

        return ResponseEntity.ok(dto);
    }

}
