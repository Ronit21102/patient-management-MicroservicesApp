package com.pm.billings_service.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService

public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBilling(BillingRequest billingRequest, StreamObserver<BillingResponse> responseObserver) {
        try {
            log.info("Received request to create billing for patient: {}", billingRequest.toString());

            // TODO: Add your business logic here to create billing record
            // For example:
            // Billing billing = billingService.createBilling(request);

            BillingResponse response = BillingResponse.newBuilder()
                .setAccountId("ACC" + System.currentTimeMillis())
                .setStatus("ACTIVE")
                .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            log.error("Error creating billing: {}", e.getMessage(), e);
            responseObserver.onError(Status.INTERNAL
                .withDescription("Failed to create billing: " + e.getMessage())
                .asRuntimeException());
        }
    }
}
