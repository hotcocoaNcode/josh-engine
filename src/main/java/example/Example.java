package example;

import co.josh.engine.Main;
import co.josh.engine.render.joshshade.JShader;
import co.josh.engine.util.annotations.hooks.Gameloop;
import co.josh.engine.util.texture.TexturePreloader;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL12;

public class Example {
    public static JShader setwhite = new JShader("/josh/shaders/resetcolor.jcsl");

    public static JShader colbynorm = new JShader("/josh/shaders/colorbynormal.jcsl");

    public static float[] ambient = {0.25f, 0.25f, 0.25f, 1f};

    @co.josh.engine.util.annotations.hooks.Startup
    public static void onStart(){
        System.out.println("Look at me! I'm a startup script!");

        TexturePreloader.load("/josh/img/");

        //OpenGL Light Setup. TODO: make lights easier
        GL12.glLightModelfv(GL12.GL_LIGHT_MODEL_AMBIENT, ambient);
        float[] diffuse = { 0.4f, 0.4f, 0.4f, 1f };
        float[] specular = { 0f, 0f, 1f, 1f };
        GL12.glLightfv(GL12.GL_LIGHT0, GL12.GL_DIFFUSE, diffuse);
        GL12.glLightfv(GL12.GL_LIGHT0, GL12.GL_SPECULAR, specular);

        Main.gameObjects.add(new Object(0, -3f, -5));
        Main.gameObjects.get(0).addComponent(new RotateComponent(Main.gameObjects.get(0)));

        Main.gameObjects.add(new Object2(-5f, -2f, -5));

    }

    public static Float movespeed = 0.25f;

    @Gameloop
    public static void movement(){
        Main.camera.updateLast();
        //Movement
        if (Main.keyboard.isKeyDown(GLFW.GLFW_KEY_W)){
            Main.camera.moveWithRotation(new Vector3f(0, 0, -1*movespeed));
        }
        if (Main.keyboard.isKeyDown(GLFW.GLFW_KEY_S)){
            Main.camera.moveWithRotation(new Vector3f(0, 0, 1*movespeed));
        }
        if (Main.keyboard.isKeyDown(GLFW.GLFW_KEY_A)){
            Main.camera.moveWithRotation(new Vector3f(-1*movespeed, 0, 0));
        }
        if (Main.keyboard.isKeyDown(GLFW.GLFW_KEY_D)){
            Main.camera.moveWithRotation(new Vector3f(1*movespeed, 0, 0));
        }
        if (Main.keyboard.isKeyDown(GLFW.GLFW_KEY_SPACE)){
            Main.camera.nextPosition.add(new Vector3f(0, 1*movespeed, 0));
        }
        if (Main.keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT)){
            Main.camera.nextPosition.add(new Vector3f(0, -1*movespeed, 0));
        }

        //Rotation
        if (Main.keyboard.isKeyDown(GLFW.GLFW_KEY_UP)){
            Main.camera.rotate(new Vector3f(-3, 0, 0));
        }
        if (Main.keyboard.isKeyDown(GLFW.GLFW_KEY_DOWN)){
            Main.camera.rotate(new Vector3f(3, 0, 0));
        }
        if (Main.keyboard.isKeyDown(GLFW.GLFW_KEY_RIGHT)){
            Main.camera.rotate(new Vector3f(0, 3, 0));
        }
        if (Main.keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT)){
            Main.camera.rotate(new Vector3f(0, -3, 0));
        }
    }

}
