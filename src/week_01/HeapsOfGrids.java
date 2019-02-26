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

public class HeapsOfGrids implements GLEventListener {

    float GRID_OFFSET = 0.04f;

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        //clear the drawing canvas
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

        gl.glColor3d(0.0, 1.0, 0.0);
        gl.glBegin(GL2.GL_LINES);

        float column = -1.0f;

        while (column <= 1.0f) {
            float row = -1.0f;

            while (row <= 1.0f) {
                float startRow = row + GRID_OFFSET;

                // draw horizontal lines
                gl.glVertex2f(row, column);
                gl.glVertex2f(startRow, column);

                // draw vertical line

                gl.glVertex2f(startRow, column);
                gl.glVertex2f(startRow, column + GRID_OFFSET);

                row = startRow;
            }

            column += GRID_OFFSET;
        }
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
        Frame frame = new Frame("Three little points");
        Random rand = new Random();
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        GLCanvas canvas = new GLCanvas(capabilities);
        HeapsOfGrids HeapsOfGrids = new HeapsOfGrids();
        canvas.addGLEventListener(HeapsOfGrids);
        frame.add(canvas);
        frame.setSize(640, 480);

        final Animator animator = new Animator(canvas);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(() -> {
                    animator.stop();
                    System.exit(0);
                }).start();
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas.requestFocusInWindow();
    }

}