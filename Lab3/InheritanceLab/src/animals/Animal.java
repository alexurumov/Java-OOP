package animals;

public class Animal {
    private String name;
    private int age;
    private String gender;

    public Animal(String name, int age, String gender) {
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
    }

    protected void setName(String name) {
//        if (name.trim().isEmpty()) {
//            throw new IllegalArgumentException("Invalid input!");
//        }
        this.name = name;
    }

    protected void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.age = age;
    }

    protected void setGender(String gender) {
//        if (name.trim().isEmpty()) {
//            throw new IllegalArgumentException("Invalid input!");
//        }
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String produceSound() {
        return "";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName())
                .append(System.lineSeparator())
                .append(this.getName()).append(" ")
                .append(this.getAge()).append(" ")
                .append(this.getGender())
                .append(System.lineSeparator());

        return sb.toString();
    }
}
