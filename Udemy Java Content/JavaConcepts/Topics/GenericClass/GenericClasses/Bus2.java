package GenericClass.GenericClasses;

public class Bus2<K,V> {
    public K getName() {
        return name;
    }

    public void setName(K name) {
        this.name = name;
    }

    public V getAddress() {
        return address;
    }

    public void setAddress(V address) {
        this.address = address;
    }

    K name;
    V address;
}
