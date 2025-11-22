import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Rekord egy oszlop definíciójához
record Column(String name, String type, boolean isNullable, boolean isUnique) {
    String toSqlDefinition() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" ").append(type);
        if (!isNullable) sb.append(" NOT NULL");
        if (isUnique) sb.append(" UNIQUE");
        return sb.toString();
    }
}

// Rekord egy tábla definíciójához
record Table(String name, List<Column> columns, List<String> primaryKeyColumns) {
    String toSql() {
        List<String> columnDefs = columns.stream()
                .map(Column::toSqlDefinition)
                .collect(Collectors.toList());

        if (!primaryKeyColumns.isEmpty()) {
            columnDefs.add("PRIMARY KEY (" + String.join(", ", primaryKeyColumns) + ")");
        }

        return "CREATE TABLE " + name + " (\n    " +
               String.join(",\n    ", columnDefs) +
               "\n);";
    }
}

// Fő osztály az adatbázis séma tervezéséhez és SQL generálásához
public class DatabaseSchemaDesigner {
    public static void main(String[] args) {
        // Felhasználói tábla definíciója
        Table usersTable = new Table(
            "Users",
            Arrays.asList(
                new Column("id", "INT", false, true),
                new Column("username", "VARCHAR(50)", false, true),
                new Column("email", "VARCHAR(100)", false, true),
                new Column("password_hash", "VARCHAR(255)", false, false)
            ),
            Arrays.asList("id") // Elsődleges kulcs
        );

        // Termék tábla definíciója
        Table productsTable = new Table(
            "Products",
            Arrays.asList(
                new Column("product_id", "INT", false, true),
                new Column("name", "VARCHAR(100)", false, false),
                new Column("price", "DECIMAL(10, 2)", false, false),
                new Column("description", "TEXT", true, false)
            ),
            Arrays.asList("product_id") // Elsődleges kulcs
        );

        // SQL DDL generálása és kiírása
        System.out.println(usersTable.toSql());
        System.out.println("\n" + productsTable.toSql());
    }
}
