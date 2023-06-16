package co.josh.engine.render.drawbuilder.commands;

import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL33;

public class GlDisableCommand implements DrawBuilderCommand {
    public int id;

    public GlDisableCommand(int id){
        this.id = id;
    }

    public void run(int GL_MODE, int i, float t){
        try{
            GL33.glDisable(id);
        } catch (Exception e) {
            System.out.println("GL_ERROR DISABLE, ITER "+i+ " MODE "+ GL33.glGetString(GL_MODE) + " FLAG " + id);
        }
    }
}
