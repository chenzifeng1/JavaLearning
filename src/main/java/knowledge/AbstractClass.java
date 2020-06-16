package knowledge;

public abstract class AbstractClass implements General{

    public int pub;
    private int pri;

    public AbstractClass(int pub, int pri) {
        this.pub = pub;
        this.pri = pri;
    }

    public AbstractClass() {
    }
}
