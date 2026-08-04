package com.automation.parser;

import com.automation.response.SimularQuitacaoResponse;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.math.BigDecimal;

public final class SoapResponseParser {

    public SimularQuitacaoResponse converter(String xml) {
        validarXml(xml);

        try {
            Document document = criarDocumentBuilder()
                    .parse(new InputSource(new StringReader(xml)));

            document.getDocumentElement().normalize();

            validarSoapFault(document);

            SimularQuitacaoResponse response =
                    new SimularQuitacaoResponse();

            response.setCodRetorno(
                    obterInteiro(document, "CodRetorno")
            );

            response.setDescricaoRetorno(
                    obterTexto(document, "DescricaoRetorno")
            );

            response.setValorMinimo(
                    obterBigDecimal(document, "ValorMinimo")
            );

            response.setValorTotal(
                    obterBigDecimal(document, "ValorTotal")
            );

            return response;

        } catch (IllegalStateException e) {
            throw e;

        } catch (Exception e) {
            throw new IllegalStateException(
                    "Erro ao converter a resposta SOAP.",
                    e
            );
        }
    }

    private DocumentBuilder criarDocumentBuilder()
            throws Exception {

        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();

        factory.setNamespaceAware(true);

        factory.setFeature(
                "http://apache.org/xml/features/disallow-doctype-decl",
                true
        );

        factory.setFeature(
                "http://xml.org/sax/features/external-general-entities",
                false
        );

        factory.setFeature(
                "http://xml.org/sax/features/external-parameter-entities",
                false
        );

        factory.setAttribute(
                XMLConstants.ACCESS_EXTERNAL_DTD,
                ""
        );

        factory.setAttribute(
                XMLConstants.ACCESS_EXTERNAL_SCHEMA,
                ""
        );

        factory.setExpandEntityReferences(false);

        return factory.newDocumentBuilder();
    }

    private void validarSoapFault(Document document) {
        NodeList faults = document.getElementsByTagNameNS(
                "*",
                "Fault"
        );

        if (faults.getLength() > 0) {
            String mensagem = faults.item(0).getTextContent().trim();

            throw new IllegalStateException(
                    "O serviço SOAP retornou Fault: " + mensagem
            );
        }
    }

    private String obterTexto(
            Document document,
            String nomeElemento
    ) {
        NodeList elementos = document.getElementsByTagNameNS(
                "*",
                nomeElemento
        );

        if (elementos.getLength() == 0) {
            throw new IllegalStateException(
                    "Elemento não encontrado no XML: " + nomeElemento
            );
        }

        String valor = elementos.item(0)
                .getTextContent()
                .trim();

        if (valor.isBlank()) {
            throw new IllegalStateException(
                    "Elemento vazio no XML: " + nomeElemento
            );
        }

        return valor;
    }

    private int obterInteiro(
            Document document,
            String nomeElemento
    ) {
        String valor = obterTexto(document, nomeElemento);

        try {
            return Integer.parseInt(valor);

        } catch (NumberFormatException e) {
            throw new IllegalStateException(
                    "Valor inteiro inválido em "
                            + nomeElemento
                            + ": "
                            + valor,
                    e
            );
        }
    }

    private BigDecimal obterBigDecimal(
            Document document,
            String nomeElemento
    ) {
        String valor = obterTexto(document, nomeElemento);

        try {
            return new BigDecimal(
                    valor.replace(",", ".")
            );

        } catch (NumberFormatException e) {
            throw new IllegalStateException(
                    "Valor decimal inválido em "
                            + nomeElemento
                            + ": "
                            + valor,
                    e
            );
        }
    }

    private void validarXml(String xml) {
        if (xml == null || xml.isBlank()) {
            throw new IllegalArgumentException(
                    "O XML da resposta SOAP não pode ser vazio."
            );
        }
    }
}