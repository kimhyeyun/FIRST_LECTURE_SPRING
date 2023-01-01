package org.example.ch4;

import org.example.ch4.calculator.ClientRequestHandler;
import org.example.ch4.calculator.domain.Calculator;
import org.example.ch4.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomWebApplicationServer {
    private final int port;

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);

    public CustomWebApplicationServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("[CustomerWebApplicationServer] start {} port.", port);

            Socket clientSocket;
            logger.info("[CustomerWebApplicationServer] waiting for client.");

            while ((clientSocket = serverSocket.accept()) != null) {
                logger.info("[CustomerWebApplicationServer] client  connected.");


                /*
                * Step 1 - 사용자 요청을 메인 Thread가 처리하도록 한다.
                * *//*

                try (InputStream in = clientSocket.getInputStream(); OutputStream out = clientSocket.getOutputStream()) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                    DataOutputStream dos = new DataOutputStream(out);

                    HttpRequest httpRequest = new HttpRequest(br);

                    if (httpRequest.isGetRequest() && httpRequest.matchPath("/calculate")) {
                        QueryStrings queryStrings = httpRequest.getQueryStrings();

                        int operand1 = Integer.parseInt(queryStrings.getValue("operand1"));
                        String operator = queryStrings.getValue("operator");
                        int operand2 = Integer.parseInt(queryStrings.getValue("operand2"));

                        int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));
                        byte[] body = String.valueOf(result).getBytes();

                        HttpResponse response = new HttpResponse(dos);
                        response.response200Header("application/json", body.length);
                        response.responseBody(body);
                    }
                }*/

                /*
                 * Step 2 - 사용자 요청이 들어올 때마다 Thread를 새로 생성해서 사용자 요청을 처리하도록 한다.
                 * 이렇게 하면 문제 ? -> 요청이 들어올 때마다 스레드를 새로 생성하기 때문에 문제 발생
                 * 스레드는 독립적인 스택 메모리 공간을 할당 받음 -> 성능이 매우 떨어짐
                 * 최악의 경우 -> 서버의 리소스가 감당 불가 -> 서버 다운 가능성 존재
                 * */
//                new Thread(new ClientRequestHandler(clientSocket)).start();

                /*
                * Step 3 - Thread Pool을 적용해 안정적인 서비스가 가능하도록 한다.
                * */

                executorService.execute(new ClientRequestHandler(clientSocket));
            }
        }
    }
}
