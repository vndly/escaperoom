package com.mauriciotogneri.escaperoom.audio;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AudioManager
{
    private final Context context;
    private final SoundPool soundPool;
    private final Map<String, Integer> soundsMap;
    private MediaPlayer player;
    private int audioPosition = 0;

    public AudioManager(Context context)
    {
        this.context = context;

        this.soundsMap = new HashMap<>();

        this.soundPool = new SoundPool(20, android.media.AudioManager.STREAM_MUSIC, 100);
        this.soundPool.setOnLoadCompleteListener((soundPool, resourceId, status) ->
        {
            if (status == 0)
            {
                playbackSound(resourceId);
            }
        });
    }

    private void loadSound(String soundPath)
    {
        AssetFileDescriptor assetDescriptor = null;

        try
        {
            assetDescriptor = context.getAssets().openFd(soundPath);
            int resourceId = soundPool.load(assetDescriptor, 1);
            soundsMap.put(soundPath, resourceId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            closeDescriptor(assetDescriptor);
        }
    }

    public void playSound(String soundPath)
    {
        if (soundsMap.containsKey(soundPath))
        {
            playbackSound(soundsMap.get(soundPath));
        }
        else
        {
            loadSound(soundPath);
        }
    }

    private void playbackSound(int resourceId)
    {
        soundPool.play(resourceId, 1f, 1f, 1, 0, 1f);
    }

    public void playAudio(String audioPath, boolean loop)
    {
        stopPlayer();

        AssetFileDescriptor assetDescriptor = null;

        try
        {
            assetDescriptor = context.getAssets().openFd(audioPath);

            player = new MediaPlayer();
            player.setDataSource(assetDescriptor.getFileDescriptor(), assetDescriptor.getStartOffset(), assetDescriptor.getLength());
            player.setLooping(loop);
            player.setVolume(1f, 1f);
            player.setOnPreparedListener(MediaPlayer::start);
            player.prepare();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            closeDescriptor(assetDescriptor);
        }
    }

    private void stopPlayer()
    {
        if (player != null)
        {
            try
            {
                player.stop();
                player.release();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void resumeAudio()
    {
        try
        {
            if ((player != null) && (!player.isPlaying()))
            {
                player.seekTo(audioPosition);
                player.start();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void pauseAudio()
    {
        if (player != null)
        {
            try
            {
                player.pause();
                audioPosition = player.getCurrentPosition();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void stopAudio()
    {
        stopPlayer();

        if (soundPool != null)
        {
            Collection<Integer> soundsIds = soundsMap.values();

            for (Integer soundId : soundsIds)
            {
                soundPool.unload(soundId);
            }

            soundPool.release();
        }
    }

    private void closeDescriptor(AssetFileDescriptor assetDescriptor)
    {
        if (assetDescriptor != null)
        {
            try
            {
                assetDescriptor.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}