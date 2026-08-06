package com.automation.integrations.soap.client;

import java.net.http.HttpResponse;

public interface SoapClientContract {

    HttpResponse<String> enviar(
            String endpoint,
            String soapAction,
            String xml
    );
}
