package it.unive.structs;

public class MyNode<E> {

    private E value;

    private MyNode<E> next;

    public MyNode (){
        this(null, null);
    }

    public MyNode (E value){
        this.setValue(value);
        this.setNext(null);
    }

    public MyNode(E value, MyNode<E> next){
        this.setValue(value);
        this.setNext(next);
    }

    public void setValue(E value) {
        this.value = value;
    }

    public void setNext(MyNode<E> next) {
        this.next = next;
    }

    public E getValue() {
        return value;
    }

    public MyNode<E> getNext() {
        return next;
    }

    public String toString () {
        return String.format("[%s: Value = %s; %s Next]", this.getClass().getSimpleName(), this.value,
                (next == null ? "Doesn't have" : "Has"));
    }

    @Override
    public int hashCode() {
        if (this.next == null)
            return (value ==null ? 1 : value.hashCode())*127;
        else {
            return 31 * (value== null ? 1 : this.value.hashCode()) + this.next.hashCode();
        }
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof MyNode) && o.hashCode()==this.hashCode();
    }

}
