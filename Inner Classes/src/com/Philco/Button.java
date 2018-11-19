package com.Philco;

/**
 * Created by PhillipsDaramola on 03/10/2017.
 */
        //Non static nested class - Hard ever used.
public class Button {

    private String title;
    private OnClickListener onClickListener;

    public Button(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setOnClickListener(OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    public void onClick(){
        this.onClickListener.onClick(this.title);
    }


    public interface OnClickListener{
        void onClick(String title);
    }
}
