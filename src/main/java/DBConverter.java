import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBConverter {

    private HikariDataSource dataSource;

    HikariConfig hikariConfig;

    public DBConverter() {
        hikariConfig = new HikariConfig("/hikary.properties");
        dataSource = new HikariDataSource(hikariConfig);
        dataSource.setMaximumPoolSize(48);
        dataSource.setMaxLifetime(60000);
        dataSource.setMinimumIdle(13);
        dataSource.setIdleTimeout(30000);
        dataSource.setLeakDetectionThreshold(48);
    }

    public void initTable(List<SchoolClass> list) {
        clearTable();
        addClasses(list);
    }

    private void clearTable() {
        try (PreparedStatement st = dataSource.getConnection().
                prepareStatement("TRUNCATE classes")) {
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addClasses(List<SchoolClass> list) {
        try(PreparedStatement st = dataSource.getConnection().
                prepareStatement("INSERT INTO classes (name, amount, teacher) VALUES (?, ?, ?)")) {

            for (SchoolClass schoolClass: list) {

                System.out.println("adding "+schoolClass.getName()+":"+schoolClass.getAmount()+":"+schoolClass.getTeacher());

                st.setString(1, schoolClass.getName());
                st.setInt(2, schoolClass.getAmount());
                st.setString(3, schoolClass.getTeacher());

                st.execute();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public SchoolClass getSchoolClass(String name) {
        try(PreparedStatement st = dataSource.getConnection().
                prepareStatement("SELECT * from classes WHERE name=?")) {

            st.setString(1, name);
            ResultSet resultSet = st.executeQuery();

            if (resultSet.next()) {
                return new SchoolClass(resultSet.getString("name"),
                        resultSet.getInt("amount"),
                        resultSet.getString("teacher"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}