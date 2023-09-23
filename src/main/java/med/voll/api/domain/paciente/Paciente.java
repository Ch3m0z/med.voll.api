package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.Direccion;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    private Boolean activo;
    @Embedded
    private Direccion direccion;

    public Paciente(PacienteRegistroDTO pacienteRegistroDTO) {
        this.nombre = pacienteRegistroDTO.nombre();
        this.email = pacienteRegistroDTO.email();
        this.telefono = pacienteRegistroDTO.telefono();
        this.documento = pacienteRegistroDTO.documento();
        this.direccion = new Direccion(pacienteRegistroDTO.direccion());
        this.activo = true;
    }

    public void actualizarDatos(PacienteActualizarDTO pacienteActualizarDTO) {
        if (pacienteActualizarDTO.nombre() != null) {
            this.nombre = pacienteActualizarDTO.nombre();
        }
        if (pacienteActualizarDTO.documento() != null) {
            this.documento = pacienteActualizarDTO.documento();
        }
        if (pacienteActualizarDTO.direccion() != null) {
            this.direccion = direccion.actualizarDatos(pacienteActualizarDTO.direccion());
        }
    }

    public void desactivarPaciente() {
        this.activo=false;
    }
}
