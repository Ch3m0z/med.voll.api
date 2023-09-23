package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.Direccion;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    private Boolean activo;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    public Medico(MedicoRegistroDTO medicoRegistroDTO) {
        this.nombre = medicoRegistroDTO.nombre();
        this.email = medicoRegistroDTO.email();
        this.telefono = medicoRegistroDTO.telefono();
        this.documento = medicoRegistroDTO.documento();
        this.especialidad = medicoRegistroDTO.especialidad();
        this.direccion = new Direccion(medicoRegistroDTO.direccion());
        this.activo = true;
    }

    public void actualizarDatos(MedicoActualizarDTO medicoActualizarDTO) {
        if (medicoActualizarDTO.nombre()!=null){
            this.nombre = medicoActualizarDTO.nombre();
        }
        if (medicoActualizarDTO.documento()!=null){
            this.documento = medicoActualizarDTO.documento();
        }
        if (medicoActualizarDTO.direccion()!=null){
            this.direccion = direccion.actualizarDatos(medicoActualizarDTO.direccion());
        }

    }

    public void desactivarMedico() {
        this.activo=false;
    }
}
