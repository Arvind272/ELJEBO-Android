package com.eljebo.serviceprovider.video_service_call;

/**
 * Created by user1 on 4/7/18.
 */

public abstract class VideoCodec {

    private final String name;

    protected VideoCodec(String name) {
        this.name = name;
    }

    /**
     * Returns the string representation of the video codec.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the name of the video codec.
     */
    @Override
    public String toString() {
        return name;
    }
}
