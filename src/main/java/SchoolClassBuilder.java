
import java.util.ArrayList;
        import java.util.List;

public class SchoolClassBuilder {

    public List<SchoolClass> buildSchoolClass(List<String> lines) {
        List<SchoolClass> bufList= new ArrayList<>();
        for (String line: lines) {
            String[] args = line.split(",");
            bufList.add(new SchoolClass(args[0],Integer.parseInt(args[1]),args[2]));
        }
        return bufList;
    }

}