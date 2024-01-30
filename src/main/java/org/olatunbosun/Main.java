package org.olatunbosun;

import org.olatunbosun.Guis.*;
import org.olatunbosun.session.SessionData;
import org.olatunbosun.session.SessionManagerMain;

public class Main {


    public static void main(String[] arg){

        SessionData sessionData = SessionManagerMain.loadUserFromFile();
//        new ListOrderGuiCustomer();
        if(sessionData == null){
            new WelcomeScreenGui();
        }else{
             new HomeGui();
        }



//           new ProfileGui();

    }
}