package co.josh.engine.render;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import co.josh.engine.util.vector.Vector3f;
import co.josh.engine.util.vector.Vector4f;

import java.util.LinkedList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class DrawBuilder {

    public Camera camera;
    public int GL_MODE;
    public List<DrawBuilderCommand> drawList;

    public DrawBuilder(Camera camera, int GL_MODE){
        this.camera = camera;
        this.GL_MODE = GL_MODE;
        this.drawList = new LinkedList<>();
    }

    public void pushQuad(Vertex3F v1, Vertex3F v2, Vertex3F v3, Vertex3F v4){
        this.GL_MODE = GL_QUADS;
        push(v1);
        push(v2);
        push(v3);
        push(v4);
    }

    public static Vector3f rotato_potato(Vector3f start, Camera camera){
        Matrix4f rotationMatrix = camera.rotationMatrix;
        org.joml.Vector3f relativeVector = start.joml_compat().sub(camera.position.joml_compat());
        org.joml.Vector3f rotatedRelativeVector = new org.joml.Vector3f();
        rotationMatrix.transformPosition(relativeVector, rotatedRelativeVector);
        return Vector3f.Vector3f_joml(new org.joml.Vector3f(rotatedRelativeVector).add(camera.position.joml_compat()));
    }

    public Vertex3F next(){
        return new Vertex3F(new Vector3f(0f,0f,0f), new Vector4f(1f, 0f, 0f, 0f));
    }

    public void push(Vertex3F vert){
        drawList.add(new VertexCommand(vert));
    }
    public void push(DrawBuilderCommand drawBuilderCommand) { drawList.add(drawBuilderCommand);}

    public void render(float t){
        GL11.glBegin(GL_MODE);
        int total = drawList.size();
        for (int i = 0; i < total; i++){
            DrawBuilderCommand command = drawList.remove(0);
            command.run(GL_MODE, i);
        }
        GL11.glEnd();
    }




}
