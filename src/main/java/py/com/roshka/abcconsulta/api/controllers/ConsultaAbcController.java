package py.com.roshka.abcconsulta.api.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.com.roshka.abcconsulta.api.domains.ConsultaAbcResponse;
import py.com.roshka.abcconsulta.api.services.IConsultaAbcService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Api("API de Consulta a ABC Color")
@RestController
@RequestMapping("/abc")
public class ConsultaAbcController {

    @Autowired
    private IConsultaAbcService service;

    @GetMapping(path="/consulta",
            produces= {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.TEXT_HTML_VALUE,
                    MediaType.TEXT_PLAIN_VALUE
            })
    @ResponseBody
    @ResponseStatus
    @ApiOperation("Retorna los resultados de una consulta hecha a la website de ABC Color, basados en un parámetro de búsqueda.")
    public ResponseEntity<?> findAllFiltered(
            @RequestParam(name = "q", required = true) String filter,
            @RequestParam(name = "f", required = false) boolean format,
            HttpServletRequest request) {

        return new ResponseEntity<>(service.findAllFiltered(filter, format), HttpStatus.OK);
    }
}
