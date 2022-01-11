package creator;

import components.SpriteRenderer;
import org.joml.Vector2f;
import util.AssetPool;
import static org.lwjgl.glfw.GLFW.*;

public class LevelEditorScene extends Scene {
    public LevelEditorScene() {

    }

    @Override
    public void init() {
        this.camera = new Camera(new Vector2f(-250, 0));

        GameObject mario = new GameObject("Mario", new Transform(new Vector2f(100, 100), new Vector2f(256, 256)));
        mario.addComponent(new SpriteRenderer(AssetPool.getTexture("assets/images/mario.png")));
        this.addGameObjectToScene(mario);

        GameObject goomba = new GameObject("Goomba", new Transform(new Vector2f(400, 100), new Vector2f(256, 256)));
        goomba.addComponent(new SpriteRenderer(AssetPool.getTexture("assets/images/goomba.png")));
        this.addGameObjectToScene(goomba);

        loadResources();
    }

    private void loadResources() {
        AssetPool.getShader("assets/shaders/default.glsl");
    }

    @Override
    public void update(float dt) {
        if (KeyListener.isKeyPressed(GLFW_KEY_RIGHT)) {
            camera.position.x += 250f * dt;
        } else if (KeyListener.isKeyPressed(GLFW_KEY_LEFT)) {
            camera.position.x -= 250f * dt;
        }

        if (KeyListener.isKeyPressed(GLFW_KEY_UP)) {
            camera.position.y += 250f * dt;
        } else if (KeyListener.isKeyPressed(GLFW_KEY_DOWN)) {
            camera.position.y -= 250f * dt;
        }

        for (GameObject go : this.gameObjects) {
            go.update(dt);
        }

        this.renderer.render();
    }
}
