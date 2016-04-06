package com.chuckwagon.controllers;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.SQLException;
import org.h2.tools.Server;

/**
 * Created by branden on 4/5/16 at 21:19.
 */
public class ChuckWagonController {

    Server dbui = null;

    @PostConstruct
    public void init() throws SQLException {
        dbui = Server.createWebServer().start();
    }

    @PreDestroy
    public void preDestory() {
        dbui.stop();
    }


}