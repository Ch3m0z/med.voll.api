package med.voll.api.domain.direccion;

import jakarta.validation.constraints.NotBlank;

public record DireccionRegistroDTO(

        @NotBlank String calle,

        @NotBlank String distrito,

        @NotBlank String ciudad, int numero,

        @NotBlank String complemento
) {}
