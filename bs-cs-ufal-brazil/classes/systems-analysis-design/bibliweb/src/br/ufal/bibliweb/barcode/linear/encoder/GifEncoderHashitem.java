package br.ufal.bibliweb.barcode.linear.encoder;

class GifEncoderHashitem
{

    public GifEncoderHashitem(int i, int j, int k, boolean flag)
    {
        rgb = i;
        count = j;
        index = k;
        isTransparent = flag;
    }

    public int rgb;
    public int count;
    public int index;
    public boolean isTransparent;
}
