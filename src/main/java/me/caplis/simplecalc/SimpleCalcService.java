package me.caplis.simplecalc;

import io.grpc.stub.StreamObserver;

import java.util.List;

public class SimpleCalcService extends SimpleCalcGrpc.SimpleCalcImplBase {

    @Override
    public void add(Operands request, StreamObserver<Result> response) {
        Double value = request
                .getOperandsList()
                .stream()
                .reduce(0d, (a, b) -> a + b);
        Result result = Result.newBuilder().setValue(value).build();
        response.onNext(result);
        response.onCompleted();
    }

    @Override
    public void sub(Operands request, StreamObserver<Result> response) {
        Double value = request
                .getOperandsList()
                .stream()
                .reduce(0d, (a, b) -> a != 0d ? a - b : b);
        Result result = Result.newBuilder().setValue(value).build();
        response.onNext(result);
        response.onCompleted();
    }

    @Override
    public void mult(Operands request, StreamObserver<Result> response) {
        Double value = request
                .getOperandsList()
                .stream()
                .reduce(0d, (a, b) -> a != 0d ? a * b : b);
        Result result = Result.newBuilder().setValue(value).build();
        response.onNext(result);
        response.onCompleted();
    }

    @Override
    public void div(Operands request, StreamObserver<Result> response) {
        Double value = request
                .getOperandsList()
                .stream()
                .reduce(0d, (a, b) -> a != 0d ? a / b : b);
        Result result = Result.newBuilder().setValue(value).build();
        response.onNext(result);
        response.onCompleted();
    }
}
