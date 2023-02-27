package com.example.nubers.util.communication;


import com.example.nubers.util.IabResult;

public interface BillingSupportCommunication {
    void onBillingSupportResult(int response);

    void remoteExceptionHappened(IabResult result);
}
