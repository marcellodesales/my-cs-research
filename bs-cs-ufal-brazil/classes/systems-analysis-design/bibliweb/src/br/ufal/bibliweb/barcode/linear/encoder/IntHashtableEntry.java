package br.ufal.bibliweb.barcode.linear.encoder;

class IntHashtableEntry
{

    IntHashtableEntry()
    {
    }

    protected Object clone()
    {
        IntHashtableEntry inthashtableentry = new IntHashtableEntry();
        inthashtableentry.hash = hash;
        inthashtableentry.key = key;
        inthashtableentry.value = value;
        inthashtableentry.next = next == null ? null : (IntHashtableEntry)next.clone();
        return inthashtableentry;
    }

    int hash;
    int key;
    Object value;
    IntHashtableEntry next;
}
