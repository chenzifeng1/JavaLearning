package knowledge;

public class MyString {
    private String string;
    private StringBuffer stringBuffer;
    private StringBuilder stringBuilder;

    public MyString(String string) {
        this.string = string;
    }

    public MyString() {
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
