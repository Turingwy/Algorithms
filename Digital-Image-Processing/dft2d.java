import ij.process.*;

public class dft2d {
    private float[] re;
    private float[] im;
    private int width, height;
    public boolean forward = true;

    public dft2d(FloatProcessor ip) {
        width = ip.getWidth();
        height = ip.getHeight();
        re = (float[]) ip.getPixels();
        im = new float[width*height];

    }

    public float[] getRe() {
        return re;
    }

    public float[] getIm() {
        return im;
    }
    public void solve() {
        float[] tmpRe = new float[width];
        float[] tmpIm = new float[width];
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                tmpRe[j] = re[width*i+j];
		tmpIm[j] = im[width*i+j];
            }

            dft d = new dft(tmpRe, tmpIm, forward);
            tmpRe = d.getRe();
            tmpIm = d.getIm();
            for(int j = 0; j < width; j++) {
                re[width*i+j] = tmpRe[j];
                im[width*i+j] = tmpIm[j];
            }
		
        }
        tmpRe = new float[height];
        tmpIm = new float[height];
        for(int i = 0; i < width; i++) {
	 tmpRe = new float[height];
        tmpIm = new float[height];
            for(int j = 0; j < height; j++) {
                tmpRe[j] = re[j*width+i];
                tmpIm[j] = im[j*width+i];
            }

            dft d = new dft(tmpRe, tmpIm, forward);
            tmpRe = d.getRe();
            tmpIm = d.getIm();

            for(int j = 0; j < height; j++) {
                re[j*width+i] = tmpRe[j];
                im[j*width+i] = tmpIm[j];
            }
        }
    }



}


