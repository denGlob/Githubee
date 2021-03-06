package com.denshiksmle.githubee.presentation.entities;

import androidx.annotation.Nullable;

public class Event<T> {

    private boolean isHandled;
    private T content;

    public Event(T content) {
        this.content = content;
    }

    public boolean isHandled() {
        return isHandled;
    }

    @Nullable
    public T getHandleContent() {
        if (isHandled) {
            return null;
        }
        isHandled = true;
        return getNoHandleContent();
    }

    public T getNoHandleContent() {
        return content;
    }
}
