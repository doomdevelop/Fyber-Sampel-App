package com.doomdev.fybersampel.presentation.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.Vector;

/**
 *
 * Message Handler class that supports buffering up of messages when the
 * activity is paused i.e. in the background.
 *
 * Created by and on 19.01.16.
 *
 *
 */
public abstract class BufferHandler extends Handler {
    protected BufferHandler(){
        super();
    }
    protected BufferHandler(Looper looper){
        super(looper);
    }
    /**
     * Message Queue Buffer
     */
    final private Vector<Message> messageQueueBuffer = new Vector<Message>();

    /**
     * Flag indicating the pause state
     */
    private volatile boolean paused;



    /**
     * Resume the handler
     */
    final public void resume() {
        paused = false;

        while (messageQueueBuffer.size() > 0 && !paused) {
            final Message msg = messageQueueBuffer.elementAt(0);
            messageQueueBuffer.removeElementAt(0);
            sendMessage(msg);
        }
    }

    /**
     * Pause the handler
     */
    final public void pause() {
        paused = true;
    }

    public void destroy(){
       messageQueueBuffer.clear();
    }
    protected abstract void processMessage(Message message);

//    protected abstract void unbind();
    /** {@inheritDoc} */
    @Override
     public void handleMessage(Message msg) {

        if (paused) {
                Message msgCopy = new Message();
                msgCopy.copyFrom(msg);
                messageQueueBuffer.add(msgCopy);
        }else{
            processMessage(msg);
        }
    }

}
