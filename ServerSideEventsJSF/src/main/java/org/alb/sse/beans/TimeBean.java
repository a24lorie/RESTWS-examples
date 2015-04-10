package org.alb.sse.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * @author alorie
 *
 */
@Named
@RequestScoped
public class TimeBean {
    String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
