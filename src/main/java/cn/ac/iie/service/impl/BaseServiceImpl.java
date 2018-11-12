package cn.ac.iie.service.impl;

import cn.ac.iie.service.BaseService;
import io.dgraph.DgraphClient;
import io.dgraph.DgraphProto;
import io.dgraph.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BaseServiceImpl implements BaseService {

    @Autowired
    @Qualifier("dgraphClient")
    private DgraphClient dgraphClient;

    @Override
    public String baseQuery(String query, Map<String, String> vars) {
        Transaction txn = dgraphClient.newTransaction();
        String result = "";
        try {
            DgraphProto.Response response = dgraphClient.newTransaction().queryWithVars(query, vars);
            result = response.getJson().toStringUtf8();
            txn.commit();
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            txn.discard();
        }
        return result;
    }
}
