package com.pm.patientservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BillingsServiceGrpcClient {
    private BillingServiceGrpc.BillingServiceBlockingStub blockingStub;

    public BillingsServiceGrpcClient(@Value("${billings.service.address:localhost}") String serverAddress, @Value("${billings.service.grpc.port:9001}") int serverPort) {
        log.info("Connecting to billing service on {}:{}/", serverAddress, serverPort);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, serverPort)
                .usePlaintext()
                .build();
        blockingStub = BillingServiceGrpc.newBlockingStub(channel);
    }

    public BillingResponse createBillingRequest(String patientId,String name,String email) {
        BillingRequest request = BillingRequest.newBuilder()
                .setPatientId(patientId)
                .setName(name)
                .setEmail(email)
                .build();

        BillingResponse response = blockingStub.createBilling(request);
        log.info("Received response from billing service: {}", response.toString());
        return response;
    }
}
