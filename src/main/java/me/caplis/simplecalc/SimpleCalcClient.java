package me.caplis.simplecalc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleCalcClient {

    private static final Logger logger = Logger.getLogger(SimpleCalcClient.class.getName());

    private final ManagedChannel channel;
    private final SimpleCalcGrpc.SimpleCalcBlockingStub stub;

    public SimpleCalcClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext(true));
    }

    public SimpleCalcClient(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.build();
        stub = SimpleCalcGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
    }

    private void info(String msg, Object... params) {
        logger.log(Level.INFO, msg, params);
    }

    private void warning(String msg, Object... params) {
        logger.log(Level.WARNING, msg, params);
    }

    public Result add(Double... values) {
        Operands request = Operands.newBuilder().addAllOperands(Arrays.asList(values)).build();
        return stub.add(request);
    }

    public Result sub(Double... values) {
        Operands request = Operands.newBuilder().addAllOperands(Arrays.asList(values)).build();
        return stub.sub(request);
    }

    public Result mult(Double... values) {
        Operands request = Operands.newBuilder().addAllOperands(Arrays.asList(values)).build();
        return stub.mult(request);
    }

    public Result div(Double... values) {
        Operands request = Operands.newBuilder().addAllOperands(Arrays.asList(values)).build();
        return stub.div(request);
    }

    public static void main(String[] args) {
        SimpleCalcClient client = new SimpleCalcClient("localhost", 8080);
        Result res;
        try {
            res = client.div(10d, 2d, 2d, 2d, 2d, 2d, 2d, 2d, 2d);
            client.info("Result -> {0}", res.getValue());
        } catch (Exception e) {
            client.warning(e.getMessage());
        }
        try {
            res = client.div(10d, 0d);
            client.info("Result -> {0}", res.getValue());

            res = client.add(11d, 11d, 11d);
            client.info("Result -> {0}", res.getValue());

            res = client.sub(10d, 2d, -3d);
            client.info("Result -> {0}", res.getValue());

            res = client.mult(10d, 2d, 11d);
            client.info("Result -> {0}", res.getValue());

            res = client.div(10d, 3d);
            client.info("Result -> {0}", res.getValue());
        } catch (Exception e) {
            client.warning(e.getMessage());
        }
    }
}
