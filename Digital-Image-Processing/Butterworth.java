import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.FFT;
import ij.plugin.filter.*;


public class bate implements PlugInFilter {

    ImagePlus imp;

    public int setup(String arg, ImagePlus imp) {
        this.imp = imp;
        return DOES_8G;
    }

    public void run(ImageProcessor ip) {
        FloatProcessor ip2=ip.duplicate().convertToFloatProcessor();
        int width=ip2.getWidth();
        int height=ip2.getHeight();

        dft2d dft=new dft2d(ip2);
        dft.solve();
	float[] re = dft.getRe();
	float[] im = dft.getIm();
	for(int i = 0; i < width*height; i++) {
		float h = H(i/width, i%width, 15000);
		re[i] *= h;
		im[i] *= h;
	}
	dft.forward = false;
	dft.solve();
        
        
        
        new ImagePlus("ÆµÓòÂË²¨",ip2.convertToByteProcessor()).show();
    }

    //ButterWorth µÍÍ¨ÂË²¨Æ÷
    private float H(int u,int v,float D1){
        double t = 1+0.414*Math.pow((u*u+v*v)/D1,2); // n=1 ½×
        return (float)(1.0/t);
    }

    //ButterWorth ¸ßÍ¨ÂË²¨Æ÷
    private float H2(int u,int v,float D1){
        double t = 1+0.414*Math.pow(D1/(u*u+v*v),1); // n=1 ½×
        return (float)(1.0/t);
    }
}
