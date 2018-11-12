package cn.ac.iie.dgraph;

import io.dgraph.DgraphClient;
import io.dgraph.DgraphGrpc;
import io.dgraph.DgraphProto;
import io.dgraph.Transaction;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Map;

@Configuration
public class DgraphClientFactory {

    @Autowired
    private DgraphConf dgraphConf;

    @Bean(name="dgraphClient")
    public DgraphClient getDgraphClient() {
        int array_length = dgraphConf.getIps().split(",").length;
        DgraphGrpc.DgraphStub[] stubs = new DgraphGrpc.DgraphStub[array_length];
        for(int i = 0; i< array_length; i++){
            String ip = dgraphConf.getIps().split(",")[i];
            ManagedChannel channel = ManagedChannelBuilder.forAddress(ip, dgraphConf.getPort()).usePlaintext().build();
            DgraphGrpc.DgraphStub stub = DgraphGrpc.newStub(channel);

            stubs[i] = stub;
        }
        DgraphClient dgraphClient = new DgraphClient(stubs);
        return dgraphClient;
    }

}
