package com.thrift.client;

import com.thrift.AccountResult;
import com.thrift.BankliucanQry;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class BankliucanQryClient {
    public static void main(String[] args) {
        BankliucanQryClient client = new BankliucanQryClient();
        client.start();
    }
    public void start() {
        TTransport transport = null;
        try {
            transport = new TSocket("127.0.0.1", 30002, 5000);
            TProtocol protocol = new TBinaryProtocol(transport);
            BankliucanQry.Client client = new BankliucanQry.Client(protocol);
            transport.open();

            AccountResult result = client.serachMoneyByAccountId(1);
            System.out.println("code="+result.code+" msg="+result.msg);
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
    }
}
