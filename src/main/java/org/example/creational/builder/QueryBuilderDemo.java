package org.example.creational.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryBuilderDemo {
    public static void main(String[] args) {
        String usersQuery = new QueryBuilder("users")
                .select("id", "name", "email")
                .where("age > 18")
                .where("country = 'Cri'")
                .orderBy("name", QueryBuilder.SortDirection.ASC)
                .limit(10)
                .execute();

        System.out.println("Consulta:\n" + usersQuery);


        String cartsQuery = new QueryBuilder("carts")
                .select("model")
                .orderBy("speed",  QueryBuilder.SortDirection.ASC)
                .execute();

        System.out.println("Consulta 2: \n" + cartsQuery);
    }
}

final class QueryBuilder {
    private final String table;
    private final List<String> conditions = new ArrayList<>();
    private List<String> fields = new ArrayList<>();
    private List<String> orderFields = new ArrayList<>();
    private Integer count;

    public QueryBuilder(String table) {
        this.table = table;
    }

    public QueryBuilder select(String... fields) {
        this.fields = Arrays.asList(fields);
        return this;
    }

    public QueryBuilder where(String condition) {
        this.conditions.add(condition);
        return this;
    }

    public QueryBuilder orderBy(String field) {
        return orderBy(field, SortDirection.ASC);
    }

    public QueryBuilder orderBy(String field, SortDirection direction) {
        this.orderFields.add("%s %s".formatted(field, direction));
        return this;
    }

    public QueryBuilder limit(int count) {
        this.count = count;
        return this;
    }

    public String execute() {
        // Select id, name, email from users where age > 18 and country = 'Cri' order by name ASC limit 10;

        String fieldList = this.fields.isEmpty()
                ? "*"
                : String.join(", ", this.fields);

        String whereClause = this.conditions.isEmpty()
                ? ""
                : "WHERE " + String.join(" AND ", conditions);

        String orderByClause = this.orderFields.isEmpty()
                ? ""
                : "ORDER BY " + String.join(", ", orderFields);

        String limitClause = this.count == null
                ? ""
                : "LIMIT " + count;


        return "SELECT %s FROM %s %s%s%s".formatted(fieldList, this.table, whereClause, orderByClause, limitClause);
    }

    public enum SortDirection {
        ASC,
        DESC
    }
}
