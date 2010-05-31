package pt.um.mrc.util.datatypes;

import pt.um.mrc.util.Constants;

/**
 * The Class PairImpl.
 * 
 * @param <K>
 *            the key type
 * @param <V>
 *            the value type
 *            
 *            
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class PairImpl<K, V> implements Pair<K, V>
{
    /** The key. */
    private K key;

    /** The value. */
    private V value;

    /**
     * Instantiates a new pair.
     */
    public PairImpl()
    {}

    /**
     * Instantiates a new pair.
     * 
     * @param key
     *            the key
     * @param value
     *            the value
     */
    public PairImpl(K key, V value)
    {
        super();
        this.key = key;
        this.value = value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.datatypes.Pair#getKey()
     */
    @Override
    public K getKey()
    {
        return key;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.datatypes.Pair#getValue()
     */
    @Override
    public V getValue()
    {
        return value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.datatypes.Pair#setKey(java.lang.Object)
     */
    @Override
    public void setKey(K k)
    {
        this.key = k;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.datatypes.Pair#setValue(java.lang.Object)
     */
    @Override
    public void setValue(V v)
    {
        this.value = v;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = Constants.HASH_CODE_PRIME;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
        boolean areEquals = true;
        areEquals &= (key == null ? key == other.getKey() : key.equals(other.getKey()));
        areEquals &= (value == null ? value == other.getValue() : value.equals(other.getValue()));
        return areEquals;
    }

}