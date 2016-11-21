package com.example.jhj0104.brainbeauty.CalDecorators.brainbeauty;

import java.io.Serializable;

/**
 * Created by jhj0104 on 2016-11-17.
 */

public class S_data implements Serializable {

    public String Date;
    public String Time;
    public String Title;
    public String Content;

    public S_data(){}
    public S_data(String Date, String Title){

        this.Date = Date;
        this.Title= Title;
    }

}
