package me.caplis.simplecalc;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;

import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleCalcLoggerInterceptor implements ServerInterceptor {
    private final Logger logger = Logger.getLogger(SimpleCalcLoggerInterceptor.class.getName());

    private void info(String msg, Object... params) {
        logger.log(Level.INFO, msg, params);
    }

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call,
            final Metadata headers,
            ServerCallHandler<ReqT, RespT> next
    ) {
        Date now = new Date();
        info(
            "{0}: {1}",
            DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(now),
            headers
        );
        return next.startCall(call, headers);
    }
}
