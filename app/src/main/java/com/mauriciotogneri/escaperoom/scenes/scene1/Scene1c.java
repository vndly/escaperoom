package com.mauriciotogneri.escaperoom.scenes.scene1;

import com.mauriciotogneri.escaperoom.R;
import com.mauriciotogneri.escaperoom.audio.Sound.Scene1;
import com.mauriciotogneri.escaperoom.scenes.BaseFragment;
import com.mauriciotogneri.escaperoom.state.StateScene1;
import com.mauriciotogneri.escaperoom.widget.InteractiveObject;

public class Scene1c extends BaseFragment<StateScene1>
{
    private InteractiveObject dark;
    private InteractiveObject drawerClose;
    private InteractiveObject drawerOpen;
    private InteractiveObject key;

    @Override
    protected void initialize(StateScene1 stateScene)
    {
        background(R.drawable.scene1c_background);

        InteractiveObject back = objectDrawable(R.drawable.ic_back);
        back.tint(R.color.selector_button_back);
        back.position(38, 43);
        back.size(100, 100);
        back.callback(() -> openScene(new Scene1a()));
        add(back);

        drawerClose = objectDrawable(R.drawable.scene1c_drawer_close);
        drawerClose.position(537, 302);
        drawerClose.size(800, 300);
        drawerClose.callback(this::openDrawer);
        add(drawerClose);

        drawerOpen = objectDrawable(R.drawable.scene1c_drawer_open);
        drawerOpen.position(345, 259);
        drawerOpen.size(1000, 600);
        drawerOpen.callback(this::openDrawer);
        add(drawerOpen);

        key = objectDrawable(R.drawable.scene1c_key);
        key.position(768, 390);
        key.size(200, 200);
        key.callback(this::getKey);
        add(key);

        dark = objectLayout(R.layout.view_interactive_object);
        dark.color(R.color.scene1_dark);
        add(dark);
    }

    @Override
    protected void onUpdate(StateScene1 state)
    {
        if (stateScene.isLightOn())
        {
            gone(dark);
        }
        else
        {
            visible(dark);
        }

        if (stateScene.isDrawerOpen())
        {
            gone(drawerClose);
            visible(drawerOpen);
        }
        else
        {
            visible(drawerClose);
            gone(drawerOpen);
        }

        if (stateScene.isDrawerOpen() && !stateScene.hasKey())
        {
            visible(key);
        }
        else
        {
            gone(key);
        }
    }

    private void openDrawer()
    {
        if (!stateScene.isDrawerOpen())
        {
            playSound(Scene1.DRAWER);
            stateScene.openDrawer();
            update();
        }
    }

    private void getKey()
    {
        if (!stateScene.hasKey())
        {
            playSound(Scene1.KEY);
            stateScene.collectKey();
            updateItems();
            update();
        }
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_scene;
    }

    @Override
    protected StateScene1 state()
    {
        return new StateScene1(getContext());
    }
}