package br.com.aulaweb.persistence;

/** represents product objects in the tutorial system*/
public class Product implements java.io.Serializable
{

    /** price per item*/
    protected double price;
    /** stock of currently available items*/
    protected int stock;
    /** product name*/
    protected String name;
    /**artificial primary key atribute*/
    private int id;

    /**
     * Insert the method's description here.
     * Creation date: (04.03.2001 11:13:27)
     * @return int
     */
    public int getId()
    {
        return id;
    }

    /**
     * Insert the method's description here.
     * Creation date: (04.03.2001 10:57:36)
     * @return java.lang.String
     */
    public java.lang.String getName()
    {
        return name;
    }

    /**
     * Insert the method's description here.
     * Creation date: (04.03.2001 10:57:36)
     * @return double
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * Insert the method's description here.
     * Creation date: (04.03.2001 10:57:36)
     * @return int
     */
    public int getStock()
    {
        return stock;
    }

    /**
     * Insert the method's description here.
     * Creation date: (04.03.2001 11:13:27)
     * @param new_id int
     */
    public void setId(int new_id)
    {
        id = new_id;
    }

    /**
     * Insert the method's description here.
     * Creation date: (04.03.2001 10:57:36)
     * @param newName java.lang.String
     */
    public void setName(java.lang.String newName)
    {
        name = newName;
    }

    /**
     * Insert the method's description here.
     * Creation date: (04.03.2001 10:57:36)
     * @param newPrice double
     */
    public void setPrice(double newPrice)
    {
        price = newPrice;
    }

    /**
     * Insert the method's description here.
     * Creation date: (04.03.2001 10:57:36)
     * @param newStock int
     */
    public void setStock(int newStock)
    {
        stock = newStock;
    }

    /**
     * Insert the method's description here.
     * Creation date: (04.03.2001 10:57:36)
     * @return int
     */
    public String toString()
    {
        return "[" + id + "] " + name + "\t\t\t price: " + price + "\t\t stock: " + stock;
    }
}
