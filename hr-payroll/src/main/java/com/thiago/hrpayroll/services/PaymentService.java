package com.thiago.hrpayroll.services;

import com.thiago.hrpayroll.entities.Payment;
import com.thiago.hrpayroll.entities.Worker;
import com.thiago.hrpayroll.feingclients.WorkerFeingClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private WorkerFeingClient workerFeingClient;

    public Payment getPayment(long workerId, int days){
        Worker worker = workerFeingClient.findById(workerId).getBody();
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
