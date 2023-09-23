package med.voll.api.domain.paciente;

import med.voll.api.domain.direccion.DireccionRegistroDTO;

public record PacienteRespuestaDTO(
        Long id,
        String nombre,
        String email,
        String telefono,
        DireccionRegistroDTO direccionRegistroDTO

) {
}
