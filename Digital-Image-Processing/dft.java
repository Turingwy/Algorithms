import java.util.*;

public class dft {
    private float[] cosTable;
    private float[] sinTable;
    private float[] re;
    private float[] im;
    private float[] gre;
    private float[] gim;
    private int len;
    public boolean forward;

    public dft(float[] gre, float[] gim, boolean f){
        len = gre.length;
	forward = f;
        cosTable = new float[len];
        sinTable = new float[len];
        re = new float[len];
        im = new float[len];
        this.gre = gre;
        this.gim = gim;
        genCos();
        genSin();
	solve();

    }

    public void genCos() {
        for(int i = 0; i < len; i++) {
            cosTable[i] = (float)Math.cos(2*Math.PI*i/len);
        }
    }

    public void genSin() {
        for(int i = 0; i < len; i++) {
            sinTable[i] = (float)Math.sin(2*Math.PI*i/len);
        }
    }

    public float[] getRe() {
        return re;
    }

    public float[] getIm() {
        return im;
    }

    public void solve() {
        for (int u = 0; u < len; u++) {
            float Resum = 0;
            float Imsum = 0;
            for (int k = 0; k < len; k++) {
                int pos = (k * u) % len;
                float sin = sinTable[pos];
                float cos = cosTable[pos];
                if (forward)
                    sin = -sin;
                Resum += gre[k] * cos - gim[k] * sin;
                Imsum += gre[k] * sin + gim[k] * cos;
            }
            re[u] = Resum;
            im[u] = Imsum;
        }
    }
}