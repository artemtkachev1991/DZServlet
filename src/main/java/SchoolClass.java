public class SchoolClass {

    private String name;
    private int amount;
    private String teacher;

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public String getTeacher() {
        return teacher;
    }

    public SchoolClass(String name, int amount, String teacher) {

        this.name = name;
        this.amount = amount;
        this.teacher = teacher;
    }
}