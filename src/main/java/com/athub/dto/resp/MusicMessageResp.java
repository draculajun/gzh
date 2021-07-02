package com.athub.dto.resp;

/**
 * @Author Wang wenjun
 */
public class MusicMessageResp extends BaseMessageResp {
    public com.athub.dto.resp.Music getMusic() {
        return Music;
    }

    public void setMusic(com.athub.dto.resp.Music music) {
        Music = music;
    }

    /**
     * 音乐
     */
    private Music Music;

}
