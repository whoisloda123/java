package com.thrift.server;

import com.thrift.BankliucanQry;
import com.thrift.query.BankliucanQryImp;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

public class BankliucanQryServer {
    private final static int DEFAULT_PORT = 30002;

    public static void main(String[] args) {
        try {
            TProcessor tprocessor = new BankliucanQry.Processor<BankliucanQry.Iface>(new BankliucanQryImp());
            TServerSocket serverTransport = new TServerSocket(DEFAULT_PORT);

            TServer.Args tArgs = new TServer.Args(serverTransport);
            tArgs.processor(tprocessor);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            TServer server = new TSimpleServer(tArgs);
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
