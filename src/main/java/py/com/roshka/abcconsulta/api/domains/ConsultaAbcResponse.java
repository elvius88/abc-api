package py.com.roshka.abcconsulta.api.domains;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class ConsultaAbcResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "fecha")
    @JsonProperty("fecha")
    private Date fecha;

    @XmlElement(name = "enlace")
    @JsonProperty("enlace")
    private String enlace;

    @XmlElement(name = "enlace_foto")
    @JsonProperty("enlace_foto")
    private String enlaceFoto;

    @XmlElement(name = "titulo")
    @JsonProperty("titulo")
    private String titulo;

    @XmlElement(name = "resumen")
    @JsonProperty("resumen")
    private String resumen;

    @XmlElement(name = "contenido_foto")
    @JsonProperty("contenido_foto")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String contenidoFoto;

    @XmlElement(name = "content_type_foto")
    @JsonProperty("content_type_foto")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String contentTypeFoto;

}
