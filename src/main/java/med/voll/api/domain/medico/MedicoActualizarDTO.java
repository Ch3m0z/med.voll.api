package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direccion.DireccionRegistroDTO;

public record MedicoActualizarDTO(
        @NotNull Long id,
        String nombre,
        String documento,
        DireccionRegistroDTO direccion

) {
}
