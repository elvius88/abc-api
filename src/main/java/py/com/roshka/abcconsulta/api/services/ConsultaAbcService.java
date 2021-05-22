package py.com.roshka.abcconsulta.api.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteStreams;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import py.com.roshka.abcconsulta.api.domains.ConsultaAbcResponse;
import py.com.roshka.abcconsulta.api.VO.ResultadoBusquedaVO;
import py.com.roshka.abcconsulta.api.exceptions.APIException;

@Service
public class ConsultaAbcService implements IConsultaAbcService {

    @Value("${api.messages.abc-url}")
    private String abcUrl;

    public List<ConsultaAbcResponse> findAllFiltered(String filter, boolean format)
    {
        return getEmployees(filter, format);
    }

    private List<ConsultaAbcResponse> getEmployees(String filter, boolean format)
    {
        if(filter == null || "".equals(filter.trim()))
            throw new APIException(HttpStatus.BAD_REQUEST, "g268", "Parametros invalidos");

        StringBuilder uri = new StringBuilder("https://www.abc.com.py/buscar/").append(filter);

        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject(uri.toString(), String.class);

        int inicio = response.indexOf("Fusion.globalContent={") + ("Fusion.globalContent={".length()-1);
        int fin = response.indexOf(";Fusion.globalContentConfig={");
        String json = response.substring(inicio, fin);
        inicio = json.indexOf("{\"data\":") + ("{\"data\":".length());
        fin = json.indexOf(",\"metadata\":{");
        json = json.substring(inicio,fin);

        List<ResultadoBusquedaVO> listaResultadoBusquedaVO;
        ObjectMapper mapper = new ObjectMapper();
        try {
            listaResultadoBusquedaVO = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, ResultadoBusquedaVO.class));
        } catch (JsonProcessingException e) {
            throw new APIException(HttpStatus.INTERNAL_SERVER_ERROR, "g100", "Error interno del servidor");
        }
        List<ConsultaAbcResponse> result = cargarResultados(listaResultadoBusquedaVO, format);
        if (result.isEmpty())
            throw new APIException(HttpStatus.NOT_FOUND, "g267", new StringBuilder("No se encuentran noticias para el texto: ").append(filter).toString());
        return result;
    }

    private List<ConsultaAbcResponse> cargarResultados(List<ResultadoBusquedaVO> listaResultadoBusquedaVO, boolean isBase64Content) {
        List<ConsultaAbcResponse> responseList = new ArrayList<>();
        for (ResultadoBusquedaVO item: listaResultadoBusquedaVO) {
            responseList.add(new ConsultaAbcResponse(
                    item.getFecha(),
                    (item.getEnlace().length>0?new StringBuilder(abcUrl).append(Arrays.stream(item.getEnlace()).findFirst().get()).toString():""),
                    item.getPromoItems()!=null && item.getPromoItems().getContenido()!=null?item.getPromoItems().getContenido().getUrl():null,
                    item.getTitulo().getContenido(),
                    item.getResumen().getContenido(),
                    (isBase64Content ? getImagenBase64(item.getPromoItems()!=null && item.getPromoItems().getContenido()!=null?item.getPromoItems().getContenido().getUrl():null):null),
                    isBase64Content?item.getPromoItems()!=null && item.getPromoItems().getContenido()!=null?item.getPromoItems().getContenido().getTipo():null:null));
        };
        return responseList;
    }

    private String getImagenBase64(String url) {

        byte[] bytes = null;
        try {
            java.net.URL imageURL = new java.net.URL(url);
            InputStream is = imageURL.openStream();
            bytes = ByteStreams.toByteArray(is);
        } catch (MalformedURLException e) {
            return "";
        } catch (IOException e) {
            return "";
        }
        return Base64.encodeBase64String(bytes);
    }


}
