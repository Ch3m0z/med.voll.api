package med.voll.api.domain.medico;

import med.voll.api.domain.direccion.DireccionRegistroDTO;

public record MedicoRespuestaDTO(
        Long id,
        String nombre,
        String email,
        String telefono,
        String documento,
        DireccionRegistroDTO direccion

) {
}
