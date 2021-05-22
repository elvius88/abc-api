package py.com.roshka.abcconsulta.api.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ContenidoImagen implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("resized_urls")
    private Object resizedUrls;
    @JsonProperty("type")
    private String tipo;
    @JsonProperty("url")
    private String url;
}
