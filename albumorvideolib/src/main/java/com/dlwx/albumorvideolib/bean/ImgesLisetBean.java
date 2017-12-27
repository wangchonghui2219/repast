package com.dlwx.albumorvideolib.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/24/024.
 */

public class ImgesLisetBean {

        private List<Image> images;

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public static class Image{
            private long date;
            private String path;

            public long getDate() {
                return date;
            }

            public void setDate(long date) {
                this.date = date;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }
        }
}
