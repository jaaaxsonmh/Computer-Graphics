package week_01;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;


/**
 * Draws three points
 * @author jwhalley
 *
 */

public class OneHundredPoints implements GLEventListener {

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        //clear the drawing canvas
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);


        for(int i = 0; i <=100; i++) {
            gl.glPointSize(8); // has to be specified before GL_POINTS begins
            gl.glBegin(GL2.GL_POINTS); //indicates we want to draw one or more points
            gl.glColor3f(getColour(), getColour(), getColour());
            gl.glVertex2f(getPosition(), getPosition());
            gl.glEnd();
        }

        gl.glFlush();

    }

    public float getPosition(){
        float min = -1.0f;
        float max = 1.0f;
        return min + new Random().nextFloat() * (max - min);
    }

    public float getColour(){
            float min = 0;
        float max = 1;
        return min + new Random().nextFloat() * (max - min);
    }


    @Override
    public void dispose(GLAutoDrawable drawable) {
        // TODO Auto-generated method stub

    }

    @Override
    public void init(GLAutoDrawable drawable) {
        // TODO Auto-generated method stub

    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        // TODO Auto-generated method stub

    }

    public static void main(String[] args) {
        Frame frame = new Frame("Three little points");
        Random rand = new Random();
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        GLCanvas canvas = new GLCanvas(capabilities);
        OneHundredPoints threePts = new OneHundredPoints();
        canvas.addGLEventListener(threePts);
        frame.add(canvas);
        frame.setSize(640, 480);

        final Animator animator = new Animator(canvas);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas.requestFocusInWindow();
    }

}