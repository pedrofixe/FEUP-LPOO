package view;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import model.Chess;
import model.Client;


public class connectMenu extends ScreenAdapter implements InputProcessor, Input.TextInputListener {

    private Chess game;
    private SpriteBatch batch;
    private Texture img;
    private String IPAddress = "";
    private Client client;

    public connectMenu(Chess game)
    {
        img = new Texture("images/menus/connectMenu.png");
        this.game = game;
        batch = game.getBatch();

        Gdx.input.setInputProcessor(this);
        Gdx.input.getTextInput(this, "Server IP", "", "");
        Gdx.input.setCatchBackKey(true);

        Gdx.graphics.requestRendering();
    }

    @Override
    public void render(float delta)
    {
        batch.begin();

        batch.draw(img, 0, 0, game.width, game.height);

        if (client != null && client.isBound())
            startGame();

        batch.end();
    }


    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {

        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        if (button == Input.Buttons.LEFT)
        {
            if (x > (float)213/1000 * width && x < (float)786/1000 * width && y > (float)480/1344 * height && y < (float)617/1344 * height) //Text box
            {
                Gdx.input.getTextInput(this, "Server IP", "", "");
            }
            else if (x > (float)213/1000 * width && x < (float)786/1000 * width && y > (float)1035/1344 * height && y < (float)1172/1344 * height) //Go Back
            {
                if (client != null)
                    client.closeConnection();

                game.setScreen(new networkMenu(game));
            }

            return true;
        }

        return false;
    }


    public void connect()
    {
        client = new Client(IPAddress);
        client.startRunning();

    }

    public void startGame()
    {
        game.setScreen(new Graphics(game, client));
    }

    @Override
    public boolean keyDown(int keycode)
    {
        if (keycode == Input.Keys.ESCAPE || keycode == Input.Keys.BACK)
        {
            game.setScreen(new networkMenu(game));
            return true;
        }

        return false;
    }


    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void input(String text)
    {
        IPAddress = text;
        connect();
    }

    @Override
    public void canceled() {

    }
}