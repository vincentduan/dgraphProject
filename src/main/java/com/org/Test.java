package com.org;

import com.google.gson.Gson;
import com.org.People;
import io.dgraph.DgraphClient;
import io.dgraph.DgraphGrpc;
import io.dgraph.DgraphProto;
import io.dgraph.Transaction;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Collections;
import java.util.Map;


public class Test {
    public static void main(String[] args) {
        ManagedChannel channel =
                ManagedChannelBuilder.forAddress("192.168.152.39", 9080).usePlaintext().build();
        DgraphGrpc.DgraphStub stub = DgraphGrpc.newStub(channel);
        ManagedChannel channel2 =
                ManagedChannelBuilder.forAddress("192.168.152.45", 9080).usePlaintext().build();
        DgraphGrpc.DgraphStub stub2 = DgraphGrpc.newStub(channel2);
        DgraphClient dgraphClient = new DgraphClient(stub, stub2);
        Transaction txn = dgraphClient.newTransaction();
        try {
            String query =
                    "query find($a: string){\n" +
                            "  find(func: eq(name@., $a)) {\n" +
                            "    directed_by{expand(_all_)}\n" +
                            "  }\n" +
                            "}\n";
            Map<String, String> vars = Collections.singletonMap("$a", "The Doors: Live in Europe (1968)");
            DgraphProto.Response response = dgraphClient.newTransaction().queryWithVars(query, vars);
            System.out.println(response.getJson().toStringUtf8());
            txn.commit();
        } finally {
            txn.discard();
        }
    }
}
