package pt.um.mrc.util.datatypes;

public class PairImpl<K, V> implements Pair<K, V>
{
    private K key;
    private V value;

    /**
     * @param key
     * @param value
     */
    public PairImpl(K key, V value)
    {
        super();
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey()
    {
        return key;
    }

    @Override
    public V getValue()
    {
        return value;
    }

    @Override
    public void setKey(K k)
    {
        this.key = k;
    }

    @Override
    public void setValue(V v)
    {
        this.value = v;
    }
}