package week_01;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;

/**
 * Draws a line based on x,y coordinates stored in an array
 * @author jwhalley
 *
 */
public class ConstellationCancer implements GLEventListener {

    private float[] linePoints = {0,0,0.5f,0.5f};

    @Override
    public void display(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();
        // clear the drawing area
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        // draw a cyan colored line from the center to the NE
        gl.glColor3d(0, 1, 1);
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2fv(linePoints, 0);
        gl.glVertex2fv(linePoints, 2);
        gl.glEnd();
        // Flush all drawing operations to the graphics card
        gl.glFlush();


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
        Frame frame = new Frame("A line built from a vertex array");
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        GLCanvas canvas = new GLCanvas(capabilities);
        ConstellationCancer lineEL = new ConstellationCancer();
        canvas.addGLEventListener(lineEL);
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