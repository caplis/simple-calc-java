package me.caplis.simplecalc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;

import java.io.IOException;
import java.util.logging.Logger;

public class SimpleCalcServer {
    private static final Logger logger = Logger.getLogger(SimpleCalcServer.class.getName());

    private final int port;
    private final Server server;

    public SimpleCalcServer(int port) {
        this(ServerBuilder.forPort(port), port);
    }

    public SimpleCalcServer(ServerBuilder<?> serverBuilder, int port) {
        this.port = port;
        this.server = serverBuilder
                .addService(ServerInterceptors.intercept(new SimpleCalcService(), new SimpleCalcLoggerInterceptor()))
                .build();
    }

    public void start() throws IOException {
        server.start();
        logger.info("Server started on port " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println(">>> shutting down gRPC server");
            SimpleCalcServer.this.stop();
            System.err.println(">>> shut down complete");
        }));
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws Exception {
        SimpleCalcServer server = new SimpleCalcServer(8080);
        server.start();
        server.blockUntilShutdown();
    }
}
