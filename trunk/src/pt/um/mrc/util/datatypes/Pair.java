package pt.um.mrc.util.datatypes;

/**
 * An interface for Pairs.
 * 
 * @param <K>
 *            the key type
 * @param <V>
 *            the value type
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public interface Pair<K, V>
{
    /**
     * Gets the key.
     * 
     * @return the key
     */
    public K getKey();

    /**
     * Gets the value.
     * 
     * @return the value
     */
    public V getValue();

    /**
     * Sets the key.
     * 
     * @param k
     *            the new key
     */
    public void setKey(K k);

    /**
     * Sets the value.
     * 
     * @param v
     *            the new value
     */
    public void setValue(V v);

    /**
     * Equals.
     * 
     * @param other
     *            the other
     * @return true, if successful
     */
    public boolean equals(Object other);

    /**
     * Hash code.
     * 
     * @return the hashcode
     */
    public int hashCode();
}
