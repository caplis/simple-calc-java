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

    public Result add(Float... values) {
        Operands request = Operands.newBuilder().addAllOperands(Arrays.asList(values)).build();
        return stub.add(request);
    }

    public Result sub(Float... values) {
        Operands request = Operands.newBuilder().addAllOperands(Arrays.asList(values)).build();
        return stub.sub(request);
    }

    public Result mult(Float... values) {
        Operands request = Operands.newBuilder().addAllOperands(Arrays.asList(values)).build();
        return stub.mult(request);
    }

    public Result div(Float... values) {
        Operands request = Operands.newBuilder().addAllOperands(Arrays.asList(values)).build();
        return stub.div(request);
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleCalcClient client = new SimpleCalcClient("localhost", 8080);
        Result res;
        try {
            // intentional error...
            res = client.div(10f, 2f, 2f);
            client.info("Result -> {0}", res.getValue());
        } catch (Exception e) {
            client.warning(e.getMessage());
        }
        try {
            res = client.add(11f, 11f, 11f);
            client.info("Result -> {0}", res.getValue());
            res = client.sub(10f, 2f, -3f);
            client.info("Result -> {0}", res.getValue());
            res = client.mult(10f, 2f, 11f);
            client.info("Result -> {0}", res.getValue());
            res = client.div(10f, 2f);
            client.info("Result -> {0}", res.getValue());
        } catch (Exception e) {
            client.warning(e.getMessage());
        }
    }
}
