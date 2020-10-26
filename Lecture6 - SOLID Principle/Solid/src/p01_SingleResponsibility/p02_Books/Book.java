package solidLab.p01_SingleResponsibility.p02_Books;

public class Book {
    public String title;

    public String author;

    public int location;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getLocation() {
        return this.location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public String turnPage(int page)
    {
        return "Current page";
    }
}
