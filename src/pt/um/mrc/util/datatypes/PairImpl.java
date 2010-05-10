package pt.um.mrc.util.datatypes;

public class PairImpl<K, V> implements Pair<K, V>
{
    private K key;
    private V value;

    public PairImpl()
    {}

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

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PairImpl<K, V> other = (PairImpl<K, V>) obj;
        if (key == null)
        {
            if (other.key != null)
                return false;
        }
        else if (!key.equals(other.key))
            return false;
        if (value == null)
        {
            if (other.value != null)
                return false;
        }
        else if (!value.equals(other.value))
            return false;
        return true;
    }

}