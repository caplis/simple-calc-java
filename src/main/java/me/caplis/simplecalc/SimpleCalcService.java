package me.caplis.simplecalc;

import io.grpc.stub.StreamObserver;

import java.util.List;

public class SimpleCalcService extends SimpleCalcGrpc.SimpleCalcImplBase {

    @Override
    public void add(Operands request, StreamObserver<Result> response) {
        Float value = request
                .getOperandsList()
                .stream()
                .reduce(0f, (a, b) -> a + b);
        Result result = Result.newBuilder().setValue(value).build();
        response.onNext(result);
        response.onCompleted();
    }

    @Override
    public void sub(Operands request, StreamObserver<Result> response) {
        Float value = request
                .getOperandsList()
                .stream()
                .reduce(0f, (a, b) -> a != 0 ? a - b : b);
        Result result = Result.newBuilder().setValue(value).build();
        response.onNext(result);
        response.onCompleted();
    }

    @Override
    public void mult(Operands request, StreamObserver<Result> response) {
        Float value = request
                .getOperandsList()
                .stream()
                .reduce(0f, (a, b) -> a != 0 ? a * b : b);
        Result result = Result.newBuilder().setValue(value).build();
        response.onNext(result);
        response.onCompleted();
    }

    @Override
    public void div(Operands request, StreamObserver<Result> response) {
        List<Float> ops = request.getOperandsList();
        if (ops.size() != 2) {
            response.onError(new Exception("Invalid arguments"));
            return;
        }
        if (ops.get(1) == 0f) {
            response.onError(new Exception("Divide by zero error..."));
            return;
        }
        Result result = Result.newBuilder().setValue(ops.get(0) / ops.get(1)).build();
        response.onNext(result);
        response.onCompleted();
    }
}
