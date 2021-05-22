package py.com.roshka.abcconsulta.api.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ResultadoBusquedaVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("_id")
    private String id;

    @JsonProperty("description")
    private Object description;

    @JsonProperty("display_date")
    private Date displayDate;

    @JsonProperty("publish_date")
    private Date fecha;

    @JsonProperty("_website_urls")
    private String[] enlace;

    @JsonProperty("headlines")
    private Cabecera titulo;

    @JsonProperty("subheadlines")
    private SubCabecera resumen;

    @JsonProperty("promo_items")
    private Imagen promoItems;

    @JsonProperty("taxonomy")
    private Object taxonomy;

}