package med.voll.api.domain.paciente;

public record PacienteListaDTO(
        Long id,
        String nombre,
        String documento,
        String email
) {
    public PacienteListaDTO(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getDocumento(),
                paciente.getEmail()
        );
    }
}

