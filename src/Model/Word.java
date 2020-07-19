package Model;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class Word implements Serializable {
    private SimpleStringProperty WordEn, WordVi, linkMp3, spelling, typeOfWord;
    private SimpleStringProperty linkImg, date, Hashtag, goiy;

    public Word(String wordEn, String wordVi, String spelling, String typeOfWord, String date, String hashtag, String goiy) {
        WordEn = new SimpleStringProperty(wordEn);
        WordVi = new SimpleStringProperty(wordVi);
        this.spelling = new SimpleStringProperty(spelling);
        this.typeOfWord = new SimpleStringProperty(typeOfWord);
        this.date = new SimpleStringProperty(date);
        Hashtag = new SimpleStringProperty(hashtag);
        this.goiy = new SimpleStringProperty(goiy);
    }

    public String getWordEn() {
        return WordEn.get();
    }

    public void setWordEn(String wordEn) {
        WordEn = new SimpleStringProperty(wordEn);
    }

    public String getWordVi() {
        return WordVi.get();
    }

    public void setWordVi(String wordVi) {
        WordVi = new SimpleStringProperty(wordVi);
    }

    public String getLinkMp3() {
        return linkMp3.get();
    }

    public void setLinkMp3(String linkMp3) {
        this.linkMp3 = new SimpleStringProperty(linkMp3);
    }

    public String getSpelling() {
        return spelling.get();
    }

    public void setSpelling(String spelling) {
        this.spelling = new SimpleStringProperty(spelling);
    }

    public String getTypeOfWord() {
        return typeOfWord.get();
    }

    public void setTypeOfWord(String typeOfWord) {
        this.typeOfWord = new SimpleStringProperty(typeOfWord);
    }

    public String getLinkImg() {
        return linkImg.get();
    }

    public void setLinkImg(String linkImg) {
        this.linkImg = new SimpleStringProperty(linkImg);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date = new SimpleStringProperty(date);
    }

    public String getHashtag() {
        return Hashtag.get();
    }

    public void setHashtag(String hashtag) {
        Hashtag = new SimpleStringProperty(hashtag);
    }

    public String getGoiy() {
        return goiy.get();
    }

    public void setGoiy(String goiy) {
        this.goiy = new SimpleStringProperty(goiy);
    }

    public Word(String wordEn, String wordVi, String linkImg, String linkMp3, String spelling, String typeOfWord, String date, String hashtag, String goiy) {
        WordEn = new SimpleStringProperty(wordEn);
        WordVi = new SimpleStringProperty(wordVi);
        this.linkMp3 = new SimpleStringProperty(linkMp3);
        this.spelling = new SimpleStringProperty(spelling);
        this.typeOfWord = new SimpleStringProperty(typeOfWord);
        this.linkImg = new SimpleStringProperty(linkImg);
        this.date = new SimpleStringProperty(date);
        Hashtag = new SimpleStringProperty(hashtag);
        this.goiy = new SimpleStringProperty(goiy);
    }
}



