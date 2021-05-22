package py.com.roshka.abcconsulta.api.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Imagen implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonProperty("basic")
    private ContenidoImagen contenido;
}
