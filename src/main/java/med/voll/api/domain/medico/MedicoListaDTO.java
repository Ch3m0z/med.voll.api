package med.voll.api.domain.medico;

public record MedicoListaDTO (
        Long id,
        String nombre,
        String especialidad,
        String documento,
        String email

){
    public MedicoListaDTO(Medico medico) {
        this(
                medico.getId(),
                medico.getNombre(),
                medico.getEspecialidad().toString(),
                medico.getDocumento(),
                medico.getEmail()
        );
    }
}
