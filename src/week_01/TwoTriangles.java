package week_01;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;

/**
 * Draws a line based on x,y coordinates stored in an array
 * @author jwhalley
 *
 */
public class TwoTriangles implements GLEventListener {


    @Override
    public void display(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();
        // clear the drawing area
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glBegin(GL.GL_TRIANGLES);

        gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
        gl.glVertex2f(0.0f, 0.3f);
        gl.glVertex2f(0.8f, 0.3f);
        gl.glVertex2f(0.4f, -0.5f);

        gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
        gl.glVertex2f(-0.4f, -0.0f);
        gl.glVertex2f(0.0f, 0.8f);
        gl.glVertex2f(0.4f, 0.0f);


        gl.glEnd();
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
        TwoTriangles twoTriangles = new TwoTriangles();
        canvas.addGLEventListener(twoTriangles);
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
