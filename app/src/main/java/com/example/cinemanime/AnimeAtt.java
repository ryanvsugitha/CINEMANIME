package com.example.cinemanime;

public class AnimeAtt {
    String animeName;
    String animeDesc;
    String animeImg;
    String animeScore;

    public AnimeAtt(String animeName, String animeDesc, String animeImg, String animeScore) {
        this.animeName = animeName;
        this.animeDesc = animeDesc;
        this.animeImg = animeImg;
        this.animeScore = animeScore;
    }

    public String getAnimeName() {
        return animeName;
    }

    public void setAnimeName(String animeName) {
        this.animeName = animeName;
    }

    public String getAnimeDesc() {
        return animeDesc;
    }

    public void setAnimeDesc(String animeDesc) {
        this.animeDesc = animeDesc;
    }

    public String getAnimeImg() {
        return animeImg;
    }

    public void setAnimeImg(String animeImg) {
        this.animeImg = animeImg;
    }

    public String getAnimeScore() {
        return animeScore;
    }

    public void setAnimeScore(String animeScore) {
        this.animeScore = animeScore;
    }
}
