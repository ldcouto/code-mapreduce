package pt.um.mrc.util.datatypes;

public interface Pair<K, V>
{
    public K getKey();

    public V getValue();

    public void setKey(K k);

    public void setValue(V v);
    
    public boolean equals(Object other);
}
