package org.olatunbosun;

import org.olatunbosun.Guis.*;
import org.olatunbosun.session.SessionData;
import org.olatunbosun.session.SessionManager;
import org.olatunbosun.session.SessionManagerMain;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

import static org.olatunbosun.database.MysqlConnection.getConnection;

public class Main {


    public static void main(String[] arg){

        SessionData sessionData = SessionManagerMain.loadUserFromFile();
        if(sessionData == null){
            new WelcomeScreenGui();
        }else{
             new HomeGui();
        }



//           new ProfileGui();

    }
}