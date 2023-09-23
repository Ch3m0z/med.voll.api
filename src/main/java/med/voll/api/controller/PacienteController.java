package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.direccion.DireccionRegistroDTO;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    public ResponseEntity<PacienteRespuestaDTO> registrarPaciente(
            @RequestBody @Valid PacienteRegistroDTO pacienteRegistroDTO,
            UriComponentsBuilder uriComponentsBuilder
    ){
        Paciente paciente = pacienteRepository.save(new Paciente(pacienteRegistroDTO));
        PacienteRespuestaDTO pacienteRespuestaDTO = new PacienteRespuestaDTO(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail(),
                paciente.getTelefono(),
                new DireccionRegistroDTO(
                        paciente.getDireccion().getCalle(),
                        paciente.getDireccion().getDistrito(),
                        paciente.getDireccion().getCiudad(),
                        paciente.getDireccion().getNumero(),
                        paciente.getDireccion().getComplemento()
                )
        );
        URI url = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(url).body(pacienteRespuestaDTO);
    }

    @GetMapping
    public ResponseEntity<Page<PacienteListaDTO>> pacienteListaDTO(@PageableDefault(size = 2) Pageable paginacion) {
        return ResponseEntity.ok(pacienteRepository.findByActivoTrue(paginacion).map(PacienteListaDTO::new));
//        return pacienteRepository.findAll(paginacion).map(PacienteListaDTO::new);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarPaciente(@RequestBody @Valid PacienteActualizarDTO pacienteActualizarDTO) {
        Paciente paciente = pacienteRepository.getReferenceById(pacienteActualizarDTO.id());
        paciente.actualizarDatos(pacienteActualizarDTO);
        return ResponseEntity.ok(new PacienteRespuestaDTO(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail(),
                paciente.getTelefono(),
                new DireccionRegistroDTO(
                        paciente.getDireccion().getCalle(),
                        paciente.getDireccion().getDistrito(),
                        paciente.getDireccion().getCiudad(),
                        paciente.getDireccion().getNumero(),
                        paciente.getDireccion().getComplemento()
                )
        ));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarPaciente(@PathVariable Long id){
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.desactivarPaciente();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteRespuestaDTO> retornarDatosPaciente(@PathVariable Long id){
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.desactivarPaciente();
        var datosPaciente = new PacienteRespuestaDTO(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail(),
                paciente.getTelefono(),
                new DireccionRegistroDTO(
                        paciente.getDireccion().getCalle(),
                        paciente.getDireccion().getDistrito(),
                        paciente.getDireccion().getCiudad(),
                        paciente.getDireccion().getNumero(),
                        paciente.getDireccion().getComplemento()
                )
        );
        return ResponseEntity.ok(datosPaciente);
    }

//    public void eliminarPaciente(@PathVariable Long id){
//        Paciente paciente = pacienteRepository.getReferenceById(id);
//        pacienteRepository.delete(paciente);
//    }
}
