package com.bank.error.threads;

import com.bank.error.core.ApplicationProperties;
import com.bank.error.core.annotation.Bean;
import com.bank.error.dao.AccountDao;
import com.bank.error.model.AccountRequest;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class TransactionThread extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionThread.class);

    @Bean
    private static AccountDao accountDao;

    private AccountRequest accountRequest;

    private Gson gson = new Gson();

    @Override
    public void run() {
        try {
            while (true) {
                accountDao.getErrorEntity().forEach(item -> {
                    accountRequest = new AccountRequest();
                    accountRequest.setId(item.getId().toString());
                    callRestService();
                });
            }
        } catch (Exception e) {
            this.start();
        }
    }

    private void callRestService() {
        try {
            URL url = new URL(ApplicationProperties.getProperty("app.account.error.service"));
            getResponse(url, gson.toJson(accountRequest));
            Thread.sleep(50000);
        } catch (Exception e) {
            LOGGER.error("Error Kontrol servis çağırım öncesi");
        }

    }

    private void getResponse(URL url, String s) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");

        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
        wr.write(s);
        wr.flush();
        wr.close();
        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            LOGGER.error("Error hatası");
        }
        conn.disconnect();
    }

}
