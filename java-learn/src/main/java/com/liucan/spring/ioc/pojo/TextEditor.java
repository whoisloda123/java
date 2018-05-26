package com.liucan.spring.ioc.pojo;

import lombok.Data;

/**
 * @author liucan
 * @date 2018/5/20
 * @brief
 */
@Data
public class TextEditor {
    private SpellChecker spellChecker;
    private String msg;
    private HelloWorld helloWorld;
    private HelloIndia helloIndia;
    private HelloChina helloChina;

    public TextEditor(SpellChecker spellChecker, String msg) {
        System.out.println("Inside TextEditor constructor." );
        this.spellChecker = spellChecker;
        this.msg = msg;
    }

    public void spellCheck() {
        spellChecker.checkSpelling();
    }
}
