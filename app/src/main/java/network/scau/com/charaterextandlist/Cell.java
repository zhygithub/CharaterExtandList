package network.scau.com.charaterextandlist;

import java.util.List;

/**
 * Created by Administrator on 2015/9/24 0024.
 */
public class Cell {

    private String firstStr;
    private char character;
    private String name;
    private List<String> sunDatas;

    public Cell(char character, String name) {
        this.character = character;
        this.name = name;
        firstStr = name.substring(0,1);
    }

    public Cell() {
    }

    public String getFirstStr() {
        return firstStr;
    }

    public void setFirstStr(String firstStr) {
        this.firstStr = firstStr;
    }

    public char getCharacter() {
        return character;
    }

    public List<String> getSunDatas() {
        return sunDatas;
    }

    public String getName() {
        return name;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public void setName(String name) {
        this.name = name;
        firstStr = name.substring(0,1);
    }

    public void setSunDatas(List<String> sunDatas) {
        this.sunDatas = sunDatas;
    }

    @Override
    public String toString() {
        String toString = "";

        toString= new String(name+character);

        if(sunDatas!=null){
            toString = toString+sunDatas.toString();
        }

        return toString;
    }
}
