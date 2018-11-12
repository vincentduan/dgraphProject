package cn.ac.iie.service;

import java.util.Map;

public interface BaseService {
    /**
     * 基本查询语句
     * query="query find($a: string){\n" +
     * "  find(func: eq(name@., $a)) {\n" +
     * "    directed_by{expand(_all_)}\n" +
     * "  }\n" +
     * "}\n"
     * vars=Collections.singletonMap("$a", "The Doors: Live in Europe (1968)");
     * @param query
     * @param vars
     * @return
     */
    String baseQuery(String query, Map<String, String> vars);
}
