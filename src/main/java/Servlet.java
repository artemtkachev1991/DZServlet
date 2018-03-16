import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/school")
public class Servlet extends HttpServlet {
    DBConverter db;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        JSONObject obj = (JSONObject) JSONValue.parse(req.getReader().readLine());

        System.out.println("requested " + obj.get("name").toString());
        SchoolClass schoolClass = db.getSchoolClass(obj.get("name").toString());

        if(schoolClass!=null) {
            resp.setContentType("application/json;charset=UTF-8");
            resp.setStatus(200);
            JSONObject obj2 = new JSONObject();
            obj2.put("name", schoolClass.getName());
            obj2.put("amount", schoolClass.getAmount());
            obj2.put("teacher", schoolClass.getTeacher());

            System.out.println(obj2.toJSONString());
            resp.getWriter().print(obj2.toJSONString());
        } else {
            resp.setContentType("text/plain");
            resp.setStatus(200);
            System.out.println("NOTFOUND");
            resp.getWriter().print("NOTFOUND");
        }
    }

    @Override
    public void init() throws ServletException {
        db = new DBConverter();
        FileReader fileReader = new FileReader();
        SchoolClassBuilder builder = new SchoolClassBuilder();

        db.initTable(builder.buildSchoolClass(fileReader.getLines()));
    }
}